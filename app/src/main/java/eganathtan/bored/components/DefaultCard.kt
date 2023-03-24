import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import eganathtan.bored.Status

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DefaultCard(
    state: Status = Status.CONTENT,
    text: String = "Random Text...",
    onNext: () -> Unit = {},
    onBookmark: () -> Unit = {},
    onError: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 250.dp)
    )
    {
        Column(
            modifier = Modifier
                .defaultMinSize(minHeight = 250.dp)
                .padding(vertical = 10.dp, horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            AnimatedContent(
                targetState = text,
                transitionSpec = {
                    fadeIn(animationSpec = tween(220, delayMillis = 90)) +
                            scaleIn(
                                initialScale = 0.50f,
                                animationSpec = tween(220, delayMillis = 90)
                            ) with
                            fadeOut(animationSpec = tween(90))
                }
            ) {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Previous")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Save")
                }
                Button(onClick = onNext) {
                    Text(text = "Next")
                }
            }
        }
    }

}