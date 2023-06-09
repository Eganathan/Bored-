package eganathtan.bored

import DefaultCard
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import eganathtan.bored.ui.theme.BoredTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoredTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Bored!") })
        }
    ) {
        Box {
            PlayInfiniteLottie(
                rawId = R.raw.waves,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 25.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val viewModel = remember { BoredViewModel() }
                val state = remember { BoredState(viewModel = viewModel) }

                DefaultCard(
                    state = state.screenState.value,
                    text = state.boredActivity.value?.activity.orEmpty(),
                    historyCount = state.boredActivityIndex.value,
                    onNext = state::loadNextIfExistsOrFetch,
                    onPrevious = state::loadPreviousIfExists
                )

            }
        }
    }

}

enum class Status { ERROR, CONTENT, LOADING, NO_INTERNET }

@Composable
fun LoadingBox() {
    PlayInfiniteLottie(R.raw.oading)
}

@Composable
fun PlayInfiniteLottie(
    rawId: Int,
    modifier: Modifier = Modifier
) {
    val sec by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(rawId))
    LottieAnimation(
        composition = sec,
        isPlaying = true,
        restartOnPlay = true,
        iterations = Int.MAX_VALUE
    )
}



