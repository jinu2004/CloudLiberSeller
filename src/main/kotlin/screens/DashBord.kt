package screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aay.compose.baseComponents.model.GridOrientation
import com.aay.compose.baseComponents.model.LegendPosition
import com.aay.compose.lineChart.LineChart
import com.aay.compose.lineChart.model.LineParameters
import com.aay.compose.lineChart.model.LineType
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import navcontroller.NavController
import networking.RouterService
import networking.dataclass.BookPreview

@ExperimentalMaterial3Api
class DashBord(val navController: NavController) {
    val apiService = RouterService.create()


    @Composable
    fun View() {
        val books = produceState<List<BookPreview>>(initialValue = emptyList(), producer = { value = apiService.getData()})
        Column {
            Text(
                text = "DashBord", color = MaterialTheme.colorScheme.onSurface,
                fontSize = TextUnit(30f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp, start = 10.dp, bottom = 30.dp)
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.Start)
            ) {

                Column(modifier = Modifier.fillMaxSize(0.5f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    IncomeChart(modifier = Modifier.fillMaxSize())
                }

                Column(
                    modifier = Modifier.fillMaxSize(0.5f),
                    verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top)
                ) {
                    DueBookList(modifier = Modifier.fillMaxSize(),books.value)
                }

            }
        }
    }
    @Composable
    private fun IncomeChart(modifier: Modifier){
        OutlinedCard(modifier = modifier) {
            Box(modifier = Modifier.fillMaxSize()){
                Row(Modifier.fillMaxWidth().padding(10.dp)) {
                    Text(
                        text = "Income Report", color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = TextUnit(20f, TextUnitType.Sp),
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.align(Alignment.CenterVertically).padding(start = 30.dp, top = 10.dp)
                    )
                }
                LineChartSample(modifier = modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 10.dp).fillMaxSize())
            }
        }
    }
    @Composable
    private fun LineChartSample(modifier: Modifier) {

        val testLineParameters = listOf(
            LineParameters(
                label = "revenue",
                data = listOf(70.0, 00.0, 50.33, 40.0, 100.500, 50.0),
                lineColor = MaterialTheme.colorScheme.primary,
                lineType = LineType.CURVED_LINE,
                lineShadow = true,
            ),
        )

        Box(modifier) {
            LineChart(
                modifier = modifier.fillMaxSize().padding(5.dp),
                linesParameters = testLineParameters,
                isGrid = true,
                gridColor = MaterialTheme.colorScheme.onPrimaryContainer,
                xAxisData = listOf("2015", "2016", "2017", "2018", "2019", "2020"),
                animateChart = true,
                showGridWithSpacer = false,
                yAxisStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray,
                ),
                xAxisStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                ),
                yAxisRange = 4,
                oneLineChart = false,
                gridOrientation = GridOrientation.VERTICAL,
                legendPosition = LegendPosition.DISAPPEAR
            )
        }
    }


    @Composable
    private fun DueBookList(modifier: Modifier,list: List<BookPreview>){
        OutlinedCard(modifier) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Due Book",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = TextUnit(20f, TextUnitType.Sp),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(0.8f).padding(start = 10.dp),
                    )

                    Text(
                        text = "see more",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                }
                LazyColumn {
                    items(items = list){
                        pendingItemCard(it)
                    }
                }
            }
        }
    }
    @Composable
    private fun pendingItemCard(item: BookPreview) {
        ElevatedCard(Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                    modifier = Modifier.width(50.dp).height(70.dp).padding(5.dp)
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
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Monospace,
                        textDecoration = null,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight(1000)
                    )
                    Text(
                        text = item.title,
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Monospace,
                        textDecoration = null,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Column(Modifier.padding(start = 10.dp, top = 5.dp).fillMaxSize(0.5f)) {
                    Text(
                        text = "Price",
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Monospace,
                        textDecoration = null,
                        maxLines = 1,
                        fontWeight = FontWeight(1000)
                    )
                    Text(
                        text = "₹${item.price}",
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Monospace,
                        textDecoration = null,
                        maxLines = 1,
                    )
                }

            }
        }
    }

}