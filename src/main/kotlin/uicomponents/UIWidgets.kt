@file:OptIn(ExperimentalMaterial3Api::class)

package uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp


@Composable
fun navigationDock(items: List<RailItem>) {
    var selected by remember { mutableStateOf(0) }
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.padding(top = 100.dp),
    ) {
        items.forEachIndexed { index, railItem ->
            val icon = if (index == selected) railItem.selectedIcon else railItem.unSelectedIcon
            val colors =
                if (index == selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            NavigationRailItem(
                selected = index == selected,
                onClick = { selected = index },
                icon = { Icon(icon, null, tint = colors) },
                modifier = Modifier,
                colors = NavigationRailItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.primary),
            )
        }
    }
}

@Composable
fun stackInform() {
    Column(Modifier.padding(top = 100.dp, start = 20.dp)) {
        ElevatedCard(
            modifier = Modifier.requiredWidthIn(min = 200.dp, max = 300.dp).requiredHeightIn(min = 100.dp, max = 200.dp)
                .padding(10.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Text(
                text = "New Orders",
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp)
            )
            Text(
                text = "8980",
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp)
            )
        }
        ElevatedCard(
            modifier = Modifier.requiredWidthIn(min = 200.dp, max = 300.dp).requiredHeightIn(min = 100.dp, max = 200.dp)
                .padding(10.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(
                text = "Due Books",
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp)
            )
            Text(
                text = "8980",
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp)
            )
        }
        ElevatedCard(
            modifier = Modifier.requiredWidthIn(min = 200.dp, max = 300.dp).requiredHeightIn(min = 100.dp, max = 200.dp)
                .padding(10.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Text(
                text = "Growth",
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp)
            )
            Text(
                text = "8980",
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp)
            )

        }
    }
}







