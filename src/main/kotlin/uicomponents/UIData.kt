package uicomponents

import androidx.compose.ui.graphics.vector.ImageVector

data class RailItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
)

data class BannerItems(
    val resource: String
)

data class StaticsData(
    val label:String,
    val content:String,
    val progress:Int
)