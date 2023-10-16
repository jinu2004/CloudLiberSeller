
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import colors.darkColor
import colors.lightColor
import navcontroller.CustomNavigationHost
import navcontroller.ListOfScreen
import navcontroller.rememberNavController
import uicomponents.navigationDock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val screens = ListOfScreen.entries.toList()
    val navController by rememberNavController(ListOfScreen.DashBord.label)
    val currentScreen by remember {
        navController.currentScreen
    }

    MaterialTheme(colorScheme = if (isSystemInDarkTheme()) darkColor else lightColor)
    {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier,
                    elevation = 0.dp,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    title = {
                        Text(
                            navController.currentScreen.value,
                            fontWeight = FontWeight(1000)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Rounded.Menu, null)
                        }
                    },
                    actions = {
                        Row {
                            IconButton(onClick = {}) {
                                Icon(Icons.Rounded.Search, null)
                            }
                            IconButton(onClick = {}) {
                                Icon(Icons.Rounded.Notifications, null)
                            }
                        }
                    },
                )
            },
        ) {
            Row(Modifier.padding(top = 80.dp)) {
                navigationDock(screens,currentScreen = currentScreen,navController = navController)
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
        ) {
            App()
        }
    }
