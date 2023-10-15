@file:OptIn(ExperimentalMaterial3Api::class)

package uicomponents

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import charts.pie.LegendOrientation
import charts.pie.PieChartConfig
import charts.pie.PieChartData
import charts.pie.PieChartWithLegend
import charts.utility.ChartAnimation
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource


@Composable
fun navigationDock(items: List<RailItem>) {
    var selected by remember { mutableStateOf(0) }
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bookChart() {
    val data = listOf(
        PieChartData(
            name = "Issued",
            value = 10.0,
            color = MaterialTheme.colorScheme.primary,
        ),
        PieChartData(
            name = "Returned",
            value = 20.0,
            color = MaterialTheme.colorScheme.secondary,
        ),
        PieChartData(
            name = "Pending",
            value = 30.0,
            color = MaterialTheme.colorScheme.tertiaryContainer,
        ),
    )
    ElevatedCard(modifier = Modifier.padding(10.dp).width(500.dp).height(500.dp)) {
        Row {
            Text(
                "Books",
                fontSize = TextUnit(20f, TextUnitType.Sp),
                modifier = Modifier.padding(start = 30.dp, top = 10.dp)
            )
        }
        PieChartWithLegend(
            pieChartData = data,
            modifier = Modifier.padding(top = 40.dp, start = 80.dp, bottom = 40.dp, end = 80.dp).fillMaxSize(),
            config = PieChartConfig(
                thickness = 20.dp,
                legendOrientation = LegendOrientation.VERTICAL,
                legendPadding = 10.dp,
            ),
            animation = ChartAnimation.Simple()
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun listOfNewOrders() {
    ElevatedCard(modifier = Modifier.width(500.dp).height(500.dp).padding(10.dp)) {
        val painterResource: Resource<Painter> = asyncPainterResource("https://www.bing.com/th?id=OADD2.9964372890914_1PNQIJVZEQHSBV7CS5&pid=21.2&c=3&w=300&h=157&dynsize=1&qlt=90")
        KamelImage(painterResource, null)
    }
}














