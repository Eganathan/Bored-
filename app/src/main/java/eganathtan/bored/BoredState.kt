package eganathtan.bored

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class BoredState(val viewModel: BoredViewModel) {

    val boredActivity: MutableState<BoredActivity?> = mutableStateOf(null)

    fun fetchRandomActivity() {
        viewModel.getRandomActivity(
            onSuccess = {
                boredActivity.value = it
                Log.e("BA","${it?.activity}")
            },
            onFailure = {

            }
        )
    }

    init {
        fetchRandomActivity()
    }
}