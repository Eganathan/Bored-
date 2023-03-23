package eganathtan.bored

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

class BoredViewModel() : ViewModel() {

    val _latestActivity: MutableState<Response<BoredActivity?>?> = mutableStateOf(null)

    fun getRandomActivity(
        onSuccess: (BoredActivity?) -> Unit,
        onFailure: (Int?) -> Unit,
    ) {
        viewModelScope.launch {
            val randomActivity = BoredApi.retrofitService.getRandomActivity()

            try {
                if (randomActivity.code() == 200 && randomActivity.body()?.activity?.isNotEmpty() == true) {
                    _latestActivity.value = randomActivity
                    onSuccess.invoke(randomActivity.body())
                } else {
                    onFailure.invoke(randomActivity.code())
                }
            } catch (e: Exception) {
                onFailure(null)
                Log.e("Error", "Get Random Activity Failure : $e")
            }

        }
    }
}