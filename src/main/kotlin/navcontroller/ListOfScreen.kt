package navcontroller

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import screens.AddBook
import screens.DashBord

enum class ListOfScreen(
    val label: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector, //
) {
    DashBord(
        label = "Home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    ),
    AddBook(
        label = "Add Book",
        selectedIcon = Icons.Filled.Edit,
        unSelectedIcon = Icons.Outlined.Edit
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNavigationHost(
    navController: NavController
) {
    NavigationHost(navController) {
        composable(ListOfScreen.DashBord.label) {
            DashBord(navController).View()
        }
        composable(ListOfScreen.AddBook.label) {
            AddBook(navController).View()
        }

    }.build()
}