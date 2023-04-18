package eganathtan.bored

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class BoredState(
    val viewModel: BoredViewModel
) {

    val boredActivity: MutableState<BoredActivity?> = mutableStateOf(null)
    val boredActivityList = mutableStateOf(listOf<BoredActivity?>())
    val boredActivityIndex: MutableState<Int> = mutableStateOf(0)

    val screenState =
        mutableStateOf(if (boredActivity.value?.activity?.isBlank() == true) Status.LOADING else Status.CONTENT)

    private fun fetchRandomActivity() {/*screenState.value = Status.LOADING*/
        viewModel.getRandomActivity(onSuccess = {
            addToListOrFetchAgain(it)/*screenState.value = Status.CONTENT*/
        }, onFailure = {
            /* screenState.value = Status.ERROR*/
        })
    }

    private fun addToListOrFetchAgain(activity: BoredActivity?) {
        if (boredActivityList.value.contains(activity)) fetchRandomActivity()
        else {
            boredActivity.value = activity
            addToActivityListAndReconfigureIndex(activity = activity)
        }
    }

    private fun addToActivityListAndReconfigureIndex(activity: BoredActivity?) {
        boredActivityList.value = boredActivityList.value.plus(activity)
        boredActivityIndex.value = boredActivityList.value.size.minus(1)
    }

    fun loadPreviousIfExists() {
        val currentIndex = boredActivityIndex.value.minus(1)
        if (currentIndex >= 0 && currentIndex < boredActivityList.value.size) {
            boredActivity.value = boredActivityList.value.getOrElse(index = currentIndex,
                defaultValue = { BoredActivity("Error") })
            boredActivityIndex.value = boredActivityIndex.value.minus(1)
        }
    }

    fun loadNextIfExistsOrFetch() {
        val currentIndex = boredActivityIndex.value.plus(1)
        if (currentIndex >= 0 && currentIndex < boredActivityList.value.size) {
            boredActivity.value = boredActivityList.value.getOrElse(index = currentIndex,
                defaultValue = { BoredActivity("Error") })
            boredActivityIndex.value = boredActivityIndex.value.plus(1)
        } else if (currentIndex == boredActivityList.value.size) {
            fetchRandomActivity()
        }
    }

    init {
        if (viewModel._latestActivity.value?.body() != null) {
            boredActivity.value = viewModel._latestActivity.value?.body()
        } else {
            fetchRandomActivity()
        }
    }
}