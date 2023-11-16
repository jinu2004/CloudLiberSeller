package navcontroller

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
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
    BookDetails(
        label = "Book Detail",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
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
        composable(ListOfScreen.BookDetails.label) {
//            BookDetails(navController)
        }

    }.build()
}