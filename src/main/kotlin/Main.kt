
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.*
import colors.darkColor
import colors.lightColor
import navcontroller.CustomNavigationHost
import navcontroller.ListOfScreen
import navcontroller.rememberNavController
import uicomponents.navigationDock

@Composable
@Preview
fun App() {
    val screens = ListOfScreen.entries.toList()
    val window = rememberWindowState()
    val navController by rememberNavController(ListOfScreen.DashBord.label)
    val currentScreen by remember {
        navController.currentScreen
    }

    MaterialTheme(colorScheme = if (isSystemInDarkTheme()) darkColor else lightColor)
    {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface,
            topBar = {
                TopAppBar(
                    modifier = Modifier,
                    elevation = 0.dp,
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    title = {
                        Text(
                            navController.currentScreen.value,
                            fontWeight = FontWeight(1000),
                            fontFamily = FontFamily(Font(resource = "fonts/Agbalumo-Regular.ttf", style = FontStyle.Normal)),
                            fontSize = 30.sp,
                            modifier = Modifier.padding(start = 40.dp)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { }, modifier = Modifier.padding(start = 15.dp)) {
                            Icon(Icons.Rounded.Menu, null, tint = MaterialTheme.colorScheme.onSurface)
                        }
                    },
                    actions = {
                        Row {
                            IconButton(onClick = {}) {
                                Icon(Icons.Outlined.Search, null, tint = MaterialTheme.colorScheme.onSurface)
                            }
                            IconButton(onClick = {

                            }) {
                                Icon(Icons.Outlined.Notifications, null, tint = MaterialTheme.colorScheme.onSurface)
                            }
                        }
                    },
                )
            },
        ) {
            it.calculateTopPadding()
            Row(Modifier.padding(top = it.calculateTopPadding())) {
                navigationDock(screens, currentScreen = currentScreen, navController = navController)
                CustomNavigationHost(navController)
            }


        }
    }
}


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        icon = painterResource("product_Logo/e70f5442-0b10-4f04-9c45-a7cf85257b18-7.ico"),
        title = "CloudLiber Seller",
        state = WindowState(placement = WindowPlacement.Floating, width = 1500.dp, height = 800.dp),
    ) {
        App()
    }
}
