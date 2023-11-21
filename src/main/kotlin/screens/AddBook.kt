package screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import navcontroller.NavController
import networking.RouterService
import networking.dataclass.BookFullDetails
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.imageio.ImageIO


class AddBook(navController: NavController) {
    private var apiService = RouterService.create()

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    @Composable
    fun View() {
        val genreExpand = remember { mutableStateOf(false) }
        val dateDialog = remember { mutableStateOf(false) }
        val dateConfirm = remember { mutableStateOf(false) }
        val subGenreExpand = remember { mutableStateOf(false) }
        var genre by rememberSaveable { mutableStateOf("") }
        var fileName by rememberSaveable { mutableStateOf("") }
        var subgenre by rememberSaveable { mutableStateOf("") }
        var bookName by rememberSaveable { mutableStateOf("") }
        var author by rememberSaveable { mutableStateOf("") }
        var coverPage by rememberSaveable { mutableStateOf("") }
        var about by rememberSaveable { mutableStateOf("") }
        var isbn by rememberSaveable { mutableStateOf("") }
        var rate by rememberSaveable { mutableStateOf("") }
        var price by rememberSaveable { mutableStateOf("") }
        var date by rememberSaveable { mutableStateOf("") }
        var publisher by rememberSaveable { mutableStateOf("") }


        val fictionSubgenre = listOf(
            "Mystery",
            "Science Fiction",
            "Historical Fiction",
            "Adventure",
            "Humor",
            "Romance",
            "Thriller",
            "Horror",
            "Fantasy",
            "Young Adult",
            "Contemporary"
        )
        val nonFictionSubgenre = listOf(
            "Science",
            "History",
            "Cooking",
            "Biography",
            "Fantasy",
            "Health and wellness",
            "Essay",
            "Technology",
            "psychology",
            "Philosophy",
            "Travel",
            "True Crime",
            "Motivational",
            "Business",
            "parenting",
        )


        val datestate = rememberDatePickerState(
            initialDisplayMode = DisplayMode.Picker,
            initialDisplayedMonthMillis = System.currentTimeMillis(),
            initialSelectedDateMillis = System.currentTimeMillis()
        )

        var showFilePicker by remember { mutableStateOf(false) }

        val colorText = TextFieldDefaults.colors(
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
            focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )

        val colors = DatePickerDefaults.colors(
            dayContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledDayContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            dayInSelectionRangeContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            headlineContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )

        Card(
            modifier = Modifier.padding(10.dp).fillMaxSize(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {

            if (datestate.selectedDateMillis != null)
                date = formatMillisecondsToDate(datestate.selectedDateMillis!!.toLong())

            FilePicker(show = showFilePicker, fileExtensions = listOf("jpg", "png")) { file ->
                if (file != null) {
                    coverPage = file.path
                }
                showFilePicker = !showFilePicker

            }

            if (dateDialog.value)
                DatePickerDialog(
                    confirmButton = {
                        TextButton(onClick = {
                            dateDialog.value = !dateDialog.value
                            dateConfirm.value = !dateConfirm.value
                        }, modifier = Modifier.align(Alignment.End).padding(10.dp)) {
                            Text("close")
                        }
                    },
                    onDismissRequest = { dateDialog.value = !dateDialog.value }
                ) {
                    DatePicker(
                        state = datestate,
                        colors = colors,
                        showModeToggle = true
                    )
                }



            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                Column(Modifier.fillMaxWidth(0.3f).fillMaxHeight()) {
                    Image(
                        painterResource("drawbles/House bookshelves-bro.png"),
                        null,
                        Modifier.fillMaxSize().padding(10.dp),
                        Alignment.Center,
                        ContentScale.Fit
                    )

                }
                LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                    item {
                        Text(
                            text = "BOOK DETAILS",
                            fontWeight = FontWeight(1000),
                            fontFamily = FontFamily(
                                Font(
                                    resource = "fonts/Mukta-Medium.ttf",
                                    style = FontStyle.Normal
                                )
                            ),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(20.dp)
                        )
                    }

                    item {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 10.dp),
                            value = bookName,
                            onValueChange = { bookName = it },
                            maxLines = 3,
                            readOnly = false,
                            label = {
                                Text(
                                    "Book Name", fontWeight = FontWeight(1000),
                                    fontFamily = FontFamily(
                                        Font(
                                            resource = "fonts/Mukta-Medium.ttf",
                                            style = FontStyle.Normal
                                        )
                                    ),
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            },
                            colors = colorText,
                            placeholder = {
                                Text(
                                    "Book Name", fontWeight = FontWeight(1000),
                                    fontFamily = FontFamily(
                                        Font(
                                            resource = "fonts/Mukta-Medium.ttf",
                                            style = FontStyle.Normal
                                        )
                                    ),
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        )
                    }

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(start = 20.dp, top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxWidth(0.45f)) {
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = genre,
                                    onValueChange = {
                                        genre = it
                                        subgenre = ""
                                    },
                                    maxLines = 1,
                                    readOnly = true,
                                    label = {
                                        Text(
                                            "Select genre",
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
                                    },
                                    trailingIcon = {
                                        IconButton(
                                            onClick = { genreExpand.value = !genreExpand.value }
                                        ) {
                                            Icon(Icons.Filled.ArrowDropDown, "")
                                        }
                                    },
                                    colors = colorText,
                                )
                                DropdownMenu(
                                    expanded = genreExpand.value,
                                    onDismissRequest = { genreExpand.value = !genreExpand.value })
                                {
                                    DropdownMenuItem(
                                        onClick = {
                                            genre = "Fiction"
                                            genreExpand.value = !genreExpand.value
                                        },
                                        text = {
                                            Text(
                                                "Fiction", fontWeight = FontWeight(1000),
                                                fontFamily = FontFamily(
                                                    Font(
                                                        resource = "fonts/Mukta-Medium.ttf",
                                                        style = FontStyle.Normal
                                                    )
                                                ),
                                                fontSize = 16.sp,
                                                modifier = Modifier.padding(start = 10.dp)
                                            )
                                        })
                                    DropdownMenuItem(
                                        onClick = {
                                            genre = "Non Fiction"
                                            genreExpand.value = !genreExpand.value
                                        },
                                        text = {
                                            Text(
                                                "Non Fiction", fontWeight = FontWeight(1000),
                                                fontFamily = FontFamily(
                                                    Font(
                                                        resource = "fonts/Mukta-Medium.ttf",
                                                        style = FontStyle.Normal
                                                    )
                                                ),
                                                fontSize = 16.sp,
                                                modifier = Modifier.padding(start = 10.dp)
                                            )
                                        }
                                    )
                                }
                            }

                            Box(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = subgenre,
                                    onValueChange = { subgenre = it },
                                    maxLines = 1,
                                    readOnly = true,
                                    label = {
                                        Text(
                                            "Select category",
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
                                    },
                                    trailingIcon = {
                                        IconButton(
                                            onClick = { subGenreExpand.value = !subGenreExpand.value }
                                        ) {
                                            Icon(Icons.Filled.ArrowDropDown, "")
                                        }
                                    },
                                    placeholder = {
                                        Text(
                                            "--Select category--",
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
                                    },
                                    colors = colorText,
                                )
                                DropdownMenu(
                                    expanded = subGenreExpand.value,
                                    onDismissRequest = { subGenreExpand.value = !subGenreExpand.value }) {

                                    if (genre == "Fiction") {
                                        fictionSubgenre.forEachIndexed { index, s ->
                                            DropdownMenuItem(
                                                onClick = {
                                                    subgenre = s
                                                    subGenreExpand.value = !subGenreExpand.value
                                                },
                                                text = {
                                                    Text(
                                                        s, fontWeight = FontWeight(1000),
                                                        fontFamily = FontFamily(
                                                            Font(
                                                                resource = "fonts/Mukta-Medium.ttf",
                                                                style = FontStyle.Normal
                                                            )
                                                        ),
                                                        fontSize = 16.sp,
                                                        modifier = Modifier.padding(start = 10.dp)
                                                    )
                                                }
                                            )
                                        }
                                    } else if (genre == "Non Fiction") {
                                        nonFictionSubgenre.forEachIndexed { index, s ->
                                            DropdownMenuItem(
                                                onClick = {
                                                    subgenre = s
                                                    subGenreExpand.value = !subGenreExpand.value
                                                },
                                                text = {
                                                    Text(
                                                        s, fontWeight = FontWeight(1000),
                                                        fontFamily = FontFamily(
                                                            Font(
                                                                resource = "fonts/Mukta-Medium.ttf",
                                                                style = FontStyle.Normal
                                                            )
                                                        ),
                                                        fontSize = 16.sp,
                                                        modifier = Modifier.padding(start = 10.dp)
                                                    )
                                                }
                                            )
                                        }
                                    }


                                }
                            }
                        }
                    }

                    item {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 10.dp),
                            value = author,
                            onValueChange = { author = it },
                            maxLines = 3,
                            readOnly = false,
                            label = {
                                Text(
                                    "Author Name", fontWeight = FontWeight(1000),
                                    fontFamily = FontFamily(
                                        Font(
                                            resource = "fonts/Mukta-Medium.ttf",
                                            style = FontStyle.Normal
                                        )
                                    ),
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            },
                            colors = colorText,
                            placeholder = {
                                Text(
                                    "Author Name", fontWeight = FontWeight(1000),
                                    fontFamily = FontFamily(
                                        Font(
                                            resource = "fonts/Mukta-Medium.ttf",
                                            style = FontStyle.Normal
                                        )
                                    ),
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        )
                    }

                    item {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 10.dp),
                            value = publisher,
                            onValueChange = { publisher = it },
                            maxLines = 3,
                            readOnly = false,
                            label = {
                                Text(
                                    "Publisher Name", fontWeight = FontWeight(1000),
                                    fontFamily = FontFamily(
                                        Font(
                                            resource = "fonts/Mukta-Medium.ttf",
                                            style = FontStyle.Normal
                                        )
                                    ),
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            },
                            colors = colorText,
                            placeholder = {
                                Text(
                                    "Publisher Name", fontWeight = FontWeight(1000),
                                    fontFamily = FontFamily(
                                        Font(
                                            resource = "fonts/Mukta-Medium.ttf",
                                            style = FontStyle.Normal
                                        )
                                    ),
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        )
                    }

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(start = 20.dp, top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(0.45f),
                                value = price,
                                onValueChange = { price = it },
                                maxLines = 1,
                                readOnly = false,
                                label = {
                                    Text(
                                        "Price", fontWeight = FontWeight(1000),
                                        fontFamily = FontFamily(
                                            Font(
                                                resource = "fonts/Mukta-Medium.ttf",
                                                style = FontStyle.Normal
                                            )
                                        ),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                },
                                colors = colorText,
                                placeholder = {
                                    Text(
                                        "Price", fontWeight = FontWeight(1000),
                                        fontFamily = FontFamily(
                                            Font(
                                                resource = "fonts/Mukta-Medium.ttf",
                                                style = FontStyle.Normal
                                            )
                                        ),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                            )
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = rate,
                                onValueChange = { rate = it },
                                maxLines = 2,
                                readOnly = false,
                                label = {
                                    Text(
                                        "Rating", fontWeight = FontWeight(1000),
                                        fontFamily = FontFamily(
                                            Font(
                                                resource = "fonts/Mukta-Medium.ttf",
                                                style = FontStyle.Normal
                                            )
                                        ),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                },
                                colors = colorText,
                                placeholder = {
                                    Text(
                                        "Rating", fontWeight = FontWeight(1000),
                                        fontFamily = FontFamily(
                                            Font(
                                                resource = "fonts/Mukta-Medium.ttf",
                                                style = FontStyle.Normal
                                            )
                                        ),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                            )
                        }
                    }

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(start = 20.dp, top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(0.45f),
                                value = coverPage,
                                onValueChange = { coverPage = it },
                                maxLines = 1,
                                readOnly = true,
                                label = {
                                    Text(
                                        "cover page", fontWeight = FontWeight(1000),
                                        fontFamily = FontFamily(
                                            Font(
                                                resource = "fonts/Mukta-Medium.ttf",
                                                style = FontStyle.Normal
                                            )
                                        ),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                },
                                colors = colorText,
                                placeholder = {
                                    Text(
                                        "cover page", fontWeight = FontWeight(1000),
                                        fontFamily = FontFamily(
                                            Font(
                                                resource = "fonts/Mukta-Medium.ttf",
                                                style = FontStyle.Normal
                                            )
                                        ),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                },
                                leadingIcon = {
                                    IconButton(
                                        onClick = { showFilePicker = !showFilePicker }
                                    ) {
                                        Icon(painterResource("drawbles/imagePlace Holder.svg"), null)
                                    }

                                }
                            )
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = date,
                                onValueChange = { date = it },
                                maxLines = 2,
                                readOnly = true,
                                label = {
                                    Text(
                                        "Date of publishing", fontWeight = FontWeight(1000),
                                        fontFamily = FontFamily(
                                            Font(
                                                resource = "fonts/Mukta-Medium.ttf",
                                                style = FontStyle.Normal
                                            )
                                        ),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                },
                                colors = colorText,
                                placeholder = {
                                    Text(
                                        "Date of publishing", fontWeight = FontWeight(1000),
                                        fontFamily = FontFamily(
                                            Font(
                                                resource = "fonts/Mukta-Medium.ttf",
                                                style = FontStyle.Normal
                                            )
                                        ),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                },
                                leadingIcon = {
                                    IconButton(
                                        onClick = { dateDialog.value = !dateDialog.value }
                                    ) {
                                        Icon(Icons.Rounded.DateRange, null)
                                    }

                                }
                            )
                        }
                    }

                    item {
                        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)) {
                            Text(
                                text = "DESCRIPTION",
                                fontWeight = FontWeight(1000),
                                fontFamily = FontFamily(
                                    Font(
                                        resource = "fonts/Mukta-Medium.ttf",
                                        style = FontStyle.Normal
                                    )
                                ),
                                fontSize = 20.sp,
                                modifier = Modifier.padding(20.dp)
                            )


                            Card(
                                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 10.dp, bottom = 30.dp)
                            ) {
                                TextField(
                                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp)
                                        .requiredHeightIn(min = 300.dp),
                                    value = about,
                                    onValueChange = { about = it },
                                    minLines = 1,
                                    readOnly = false,
                                    placeholder = {
                                        Text(
                                            "sample text", fontWeight = FontWeight(1000),
                                            fontFamily = FontFamily(
                                                Font(
                                                    resource = "fonts/Mukta-Medium.ttf",
                                                    style = FontStyle.Normal
                                                )
                                            ),
                                            fontSize = 16.sp,
                                            modifier = Modifier.padding(start = 10.dp),
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }
                                )
                            }


                        }
                    }

                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            FloatingActionButton(onClick = {
                                if (bookName.isNotBlank() && coverPage.isNotBlank() && genre.isNotBlank() && subgenre.isNotBlank()) {
                                    val bookData = BookFullDetails(
                                        about,
                                        author,
                                        coverPage,
                                        date,
                                        genre,
                                        "1234",
                                        price,
                                        publisher,
                                        rate,
                                        subgenre,
                                        bookName
                                    )
                                    uploadDetails(bookData)
                                }

                            }) {
                                Icon(Icons.Rounded.Add, "")
                            }
                        }
                    }
                }

            }


        }
    }


    private fun formatMillisecondsToDate(milliseconds: Long): String {
        val instant = Instant.ofEpochMilli(milliseconds)
        val zoneId = ZoneId.systemDefault() // You can change this to your desired time zone
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        println("time${formatter.toString()}")
        return formatter.format(instant.atZone(zoneId))
    }

    private fun uploadDetails(bookFullDetails: BookFullDetails) {
        val scope = CoroutineScope(Dispatchers.IO)
        val inputFile = File(bookFullDetails.coverPage)
        try {
            val byteArray = imageToByteArray(inputFile)

            scope.launch {
                withContext(Dispatchers.Main) {
                    apiService.uploadDetails(bookFullDetails, byteArray)
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