package charts.pie

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import charts.utility.ChartAnimation

/**
 * Version of [PieChart] with legend.
 *
 * @param legendItemLabel Composable to use to represent the item in the legend
 *
 * @see PieChart
 */
@Composable
fun PieChartWithLegend(
    pieChartData: List<PieChartData>,
    modifier: Modifier = Modifier,
    animation: ChartAnimation = ChartAnimation.Simple(),
    config: PieChartConfig = PieChartConfig(),
    legendItemLabel: @Composable (PieChartData) -> Unit = PieDefaults.LegendItemLabel,
) {
    when (config.legendOrientation) {
        LegendOrientation.HORIZONTAL ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(config.legendPadding),
                modifier = modifier,
            ) {
                PieChart(
                    data = pieChartData,
                    modifier = Modifier.weight(1f),
                    animation = animation,
                    config = config,
                )
                PieChartLegend(
                    data = pieChartData,
                    animation = animation,
                    legendItemLabel = legendItemLabel,
                    config = config,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        LegendOrientation.VERTICAL ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(config.legendPadding),
                modifier = modifier,
            ) {
                PieChart(
                    data = pieChartData,
                    modifier = Modifier.fillMaxHeight(),
                    animation = animation,
                    config = config,
                )
                PieChartLegend(
                    data = pieChartData,
                    animation = animation,
                    legendItemLabel = legendItemLabel,
                    config = config,
                    modifier = Modifier.wrapContentWidth(),
                )
            }
    }
}
