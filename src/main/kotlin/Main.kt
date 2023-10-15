
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import colors.darkColor
import colors.lightColor
import uicomponents.RailItem
import uicomponents.bookChart
import uicomponents.listOfNewOrders
import uicomponents.navigationDock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val list = arrayListOf<RailItem>()
    list.add(
        RailItem(
            "Home",
            Icons.Filled.Home,
            Icons.Outlined.Home,
        )
    )
    list.add(
        RailItem(
            "Emails",
            Icons.Filled.Email,
            Icons.Outlined.Email,
        )
    )
    list.add(
        RailItem(
            "Cart",
            Icons.Filled.ShoppingCart,
            Icons.Outlined.ShoppingCart,
        )
    )
    list.add(
        RailItem(
            "users",
            Icons.Filled.Person,
            Icons.Outlined.Person,
        )
    )
    MaterialTheme(colorScheme = if (isSystemInDarkTheme()) darkColor else lightColor)
    {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier,
                    elevation = 0.dp,
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    title = { Text("DashBord") },
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
            Row(Modifier.padding(top = 80.dp), horizontalArrangement = Arrangement.Start) {
                navigationDock(list)
                Column {
                    Row(modifier = Modifier.height(400.dp)) {
                        bookChart()
                        listOfNewOrders()
                    }
                }

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
