package screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
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
import navcontroller.NavController

@ExperimentalMaterial3Api
class DashBord(val navController: NavController) {

    @Composable
    fun View() {
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
                    DueBookList(modifier = Modifier.fillMaxSize())
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
    private fun DueBookList(modifier: Modifier){
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

                }
            }
        }
    }

}