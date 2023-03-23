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

            Text(
                text = text,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
            )

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