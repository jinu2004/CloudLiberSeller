package screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import navcontroller.NavController
import networking.AplServices
import networking.dataclass.BookPreview
import uicomponents.DonutChartSample
import uicomponents.NewOrder
import uicomponents.pendingBook

@Composable
fun DashBord(navController: NavController){
    val service = AplServices.create()

    val posts = produceState<List<BookPreview>>(
        initialValue = emptyList(),
        producer = {
            value = service.getData()
        }
    )
    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            DonutChartSample(Modifier.fillMaxWidth(0.5f).fillMaxHeight(0.5f))
        }

        Row(Modifier.fillMaxSize(1f)) {
            NewOrder(posts.value, modifier = Modifier.fillMaxHeight().fillMaxWidth(0.5f).padding(top = 10.dp, start = 10.dp))
            pendingBook(posts.value, modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(top = 10.dp, start = 10.dp, end = 10.dp))
        }

    }
}
