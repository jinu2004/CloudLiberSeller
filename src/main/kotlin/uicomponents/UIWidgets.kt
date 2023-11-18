@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package uicomponents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aay.compose.baseComponents.model.LegendPosition
import com.aay.compose.donutChart.DonutChart
import com.aay.compose.donutChart.model.PieChartData
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import navcontroller.ListOfScreen
import navcontroller.NavController
import networking.dataclass.BookPreview


@Composable
fun navigationDock(items: List<ListOfScreen>,currentScreen:String,modifier: Modifier = Modifier,navController: NavController) {
    NavigationRail(
        header = {
                 Card(modifier = Modifier.width(10.dp).height(10.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)){}
        },
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.surface,
        modifier = modifier,
    ) {
        items.forEachIndexed { _, railItem ->
            val icon = if (currentScreen == railItem.label) railItem.selectedIcon else railItem.unSelectedIcon
            val colors =
                if (currentScreen == railItem.label) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            NavigationRailItem(
                selected = currentScreen == railItem.label,
                onClick = { navController.navigate(railItem.label) },
                icon = { Icon(icon, null, tint = colors) },
                modifier = Modifier,
                colors = NavigationRailItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.primary),
            )
        }
    }
}


@Composable
fun NewOrder(list: List<BookPreview>, modifier: Modifier = Modifier) {
    Column( modifier = modifier) {
        Text(
            "New Orders",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight(1000),
        )
        LazyColumn {
            items(list) {
                newOrderCard(it)
            }

        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun newOrderCard(item: BookPreview) {
    ElevatedCard(Modifier.padding(top = 10.dp)){
        Row(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ElevatedCard(
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                modifier = Modifier.width(80.dp).height(90.dp).padding(5.dp)
            ) {
                val painterResource: Resource<Painter> = asyncPainterResource(item.coverpage)
                KamelImage(
                    resource = painterResource,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                )
            }

            Column(Modifier.padding(start = 20.dp, top = 5.dp).fillMaxSize(0.5f)) {
                Text(
                    text = "Tittle",
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = null,
                    maxLines = 1,
                    fontWeight = FontWeight(1000)
                )
                Text(
                    text = item.title,
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = null,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Column(Modifier.padding(start = 20.dp, top = 5.dp).fillMaxSize(0.5f)) {
                Text(
                    text = "Price",
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = null,
                    maxLines = 1,
                    fontWeight = FontWeight(1000)
                )
                Text(
                    text = "₹${item.price}",
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = null,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

        }
    }
}

@Composable
fun pendingItemCard(item: BookPreview) {
    ElevatedCard(Modifier.padding(top = 10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ElevatedCard(
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                modifier = Modifier.width(80.dp).height(90.dp).padding(5.dp)
            ) {
                val painterResource: Resource<Painter> = asyncPainterResource(item.coverpage)
                KamelImage(
                    resource = painterResource,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                )
            }

            Column(Modifier.padding(start = 20.dp, top = 5.dp).fillMaxSize(0.5f)) {
                Text(
                    text = "Tittle",
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = null,
                    maxLines = 1,
                    fontWeight = FontWeight(1000)
                )
                Text(
                    text = item.title,
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = null,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Column(Modifier.padding(start = 20.dp, top = 5.dp).fillMaxSize(0.5f)) {
                Text(
                    text = "Price",
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = null,
                    maxLines = 1,
                    fontWeight = FontWeight(1000)
                )
                Text(
                    text = "₹${item.price}",
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = null,
                    maxLines = 1,
                )
            }

        }
    }
}

@Composable
fun pendingBook(list: List<BookPreview>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            "Pending",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight(1000),
        )
        LazyColumn {
            items(list) {
                pendingItemCard(it)

            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonutChartSample(modifier: Modifier = Modifier) {
    val testPieChartData: List<PieChartData> = listOf(
        PieChartData(
            partName = "Issued",
            data = 500.0,
            color = Color(0xFF0B666A),
        ),
        PieChartData(
            partName = "Returned",
            data = 700.0,
            color = Color(0xFF35A29F),
        ),
        PieChartData(
            partName = "Pending",
            data = 500.0,
            color = Color(0xFF97FEED),
        ),
    )

    ElevatedCard(modifier = modifier) {
        Row(Modifier.padding(start = 30.dp, top = 10.dp)) {
            Text(
                "Books",
                fontWeight = FontWeight(1000)
            )
        }
            DonutChart(
                modifier = Modifier.fillMaxSize(),
                pieChartData = testPieChartData,
                centerTitle = "Books",
                centerTitleStyle = TextStyle(color = Color(0xFF071952)),
                outerCircularColor = Color.LightGray,
                innerCircularColor = Color.Gray,
                ratioLineColor = Color.LightGray,
                legendPosition = LegendPosition.TOP
            )
        }
    }


















