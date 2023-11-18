package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
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
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import navcontroller.NavController
import networking.RouterService
import networking.dataclass.BookPreview
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO

@ExperimentalMaterial3Api
class DashBord(val navController: NavController) {
    private var apiService = RouterService.create()


    @Composable
    fun View() {

        var showFilePicker by remember { mutableStateOf(false)}
        val books =
            produceState<List<BookPreview>>(initialValue = emptyList(), producer = { value = apiService.getData() })
        Column(modifier = Modifier.padding(10.dp)) {
            val fileType = listOf("jpg")
            FilePicker(show = showFilePicker, fileExtensions = fileType) { file ->
                println(file?.path)
                uploadImage(file!!.path)
                showFilePicker = !showFilePicker

            }
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Start)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.7f).fillMaxHeight(0.5f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Column(Modifier.fillMaxWidth(0.3f).fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Card(
                                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.33f),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Card(
                                        modifier = Modifier.fillMaxWidth(0.3f).fillMaxHeight(0.5f)
                                            .padding(start = 20.dp),
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                                        onClick = {showFilePicker = !showFilePicker}
                                    ) {
                                        Image(
                                            painter = painterResource("drawbles/book_2_FILL0_wght400_GRAD0_opsz24.svg"),
                                            contentScale = ContentScale.Fit,
                                            contentDescription = "",
                                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                                            modifier = Modifier.fillMaxSize().padding(15.dp)
                                        )
                                    }
                                    Column(
                                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f),
                                        verticalArrangement = Arrangement.spacedBy(1.dp),
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Text(
                                            "Borrowed Books",
                                            fontWeight = FontWeight(1000),
                                            fontFamily = FontFamily(
                                                Font(
                                                    resource = "fonts/Mukta-Medium.ttf",
                                                    style = FontStyle.Normal
                                                )
                                            ),
                                            fontSize = 16.sp,
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                        Text(
                                            "10",
                                            fontWeight = FontWeight(1000),
                                            fontFamily = FontFamily(
                                                Font(
                                                    resource = "fonts/Mukta-Bold.ttf",
                                                    style = FontStyle.Normal
                                                )
                                            ),
                                            fontSize = 20.sp,
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                    }
                                }
                            }


                            Card(
                                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.48f),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Card(
                                        modifier = Modifier.fillMaxWidth(0.3f).fillMaxHeight(0.5f)
                                            .padding(start = 20.dp),
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
                                    ) {
                                        Image(
                                            painter = painterResource("drawbles/pending_actions_FILL0_wght400_GRAD0_opsz24.svg"),
                                            contentScale = ContentScale.Fit,
                                            contentDescription = "",
                                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary),
                                            modifier = Modifier.fillMaxSize().padding(15.dp)
                                        )
                                    }
                                    Column(
                                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f),
                                        verticalArrangement = Arrangement.spacedBy(1.dp),
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Text(
                                            "Overdue Book",
                                            fontWeight = FontWeight(1000),
                                            fontFamily = FontFamily(
                                                Font(
                                                    resource = "fonts/Mukta-Medium.ttf",
                                                    style = FontStyle.Normal
                                                )
                                            ),
                                            fontSize = 16.sp,
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                        Text(
                                            "40",
                                            fontWeight = FontWeight(1000),
                                            fontFamily = FontFamily(
                                                Font(
                                                    resource = "fonts/Mukta-Bold.ttf",
                                                    style = FontStyle.Normal
                                                )
                                            ),
                                            fontSize = 20.sp,
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                    }
                                }
                            }

                            Card(
                                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Card(
                                        modifier = Modifier.fillMaxWidth(0.3f).fillMaxHeight(0.5f)
                                            .padding(start = 20.dp),
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
                                    ) {
                                        Image(
                                            painter = painterResource("drawbles/people_black_24dp.svg"),
                                            contentScale = ContentScale.Fit,
                                            contentDescription = "",
                                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary),
                                            modifier = Modifier.fillMaxSize().padding(15.dp)
                                        )
                                    }
                                    Column(
                                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f),
                                        verticalArrangement = Arrangement.spacedBy(0.dp),
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Text(
                                            "Peoples",
                                            fontWeight = FontWeight(1000),
                                            fontFamily = FontFamily(
                                                Font(
                                                    resource = "fonts/Mukta-Medium.ttf",
                                                    style = FontStyle.Normal
                                                )
                                            ),
                                            fontSize = 16.sp,
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                        Text(
                                            "1000",
                                            fontWeight = FontWeight(1000),
                                            fontFamily = FontFamily(
                                                Font(
                                                    resource = "fonts/Mukta-Bold.ttf",
                                                    style = FontStyle.Normal
                                                )
                                            ),
                                            fontSize = 20.sp,
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                    }
                                }
                            }
                        }
                        IncomeChart(modifier = Modifier.fillMaxWidth().fillMaxHeight())

                    }
                }


                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top)
                ) {
                    DueBookList(modifier = Modifier.fillMaxSize(), books.value)
                    println(books.value)
                }

            }
        }
    }

    @Composable
    private fun IncomeChart(modifier: Modifier) {
        Card(modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
            Box(modifier = Modifier.fillMaxSize()) {
                Row(Modifier.fillMaxWidth().padding(10.dp)) {
                    Text(
                        text = "Income Report", color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = TextUnit(20f, TextUnitType.Sp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(resource = "fonts/Mukta-Medium.ttf", weight = FontWeight.Bold)),
                        modifier = modifier.align(Alignment.CenterVertically).padding(start = 30.dp, top = 10.dp)
                    )
                }
                LineChartSample(
                    modifier = modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 10.dp).fillMaxSize()
                )
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
            LineParameters(
                label = "orders",
                data = listOf(00.0, 30.0, 70.33, 40.0, 50.500, 10.0),
                lineColor = MaterialTheme.colorScheme.tertiaryContainer,
                lineType = LineType.CURVED_LINE,
                lineShadow = true,
            )
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
                legendPosition = LegendPosition.TOP
            )
        }
    }


    @Composable
    private fun DueBookList(modifier: Modifier, list: List<BookPreview>) {
        Card(modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
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
                    items(items = list) {
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



    private fun uploadImage(path: String){
        val scope = CoroutineScope(Dispatchers.IO)
        val inputFile = File(path)
        try {
            val byteArray = imageToByteArray(inputFile)

            scope.launch {
                withContext(Dispatchers.Main){
                    apiService.uploadImageToGoogleStorage(byteArray)
                }
            }
            println("Image converted to ByteArray successfully.")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
    private fun imageToByteArray(file: File): ByteArray {
        val bufferedImage: BufferedImage = ImageIO.read(file)
        val baos = ByteArrayOutputStream()
        ImageIO.write(bufferedImage, "png", baos)
        return baos.toByteArray()
    }





}