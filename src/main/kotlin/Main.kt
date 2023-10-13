import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {

    MaterialTheme {
        Scaffold(topBar = {topBar()}) {  }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

@Composable
fun topBar() {
    TopAppBar(
        modifier = Modifier,
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        Icon(Icons.Outlined.Home,
            contentDescription = "home",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(10.dp))
    }
}
