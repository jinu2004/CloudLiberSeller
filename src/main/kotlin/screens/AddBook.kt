package screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import navcontroller.NavController
import java.time.Instant
import java.time.LocalDateTime


class AddBook(navController: NavController) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun View() {
        val genreExpand = remember { mutableStateOf(false) }
        val dateDialog = remember { mutableStateOf(false) }
        val subGenreExpand = remember { mutableStateOf(false) }
        var genre by rememberSaveable { mutableStateOf("Fiction") }
        var subgenre by rememberSaveable { mutableStateOf("--select--") }
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


        var datestate = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)

        val colorText = TextFieldDefaults.colors(
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        )

        val colors = DatePickerDefaults.colors(
            dayContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledDayContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            dayInSelectionRangeContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            headlineContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxSize().padding(10.dp)
        ) {
            if (dateDialog.value)
                DatePickerDialog(
                    confirmButton = {
                        TextButton(onClick = {dateDialog.value = !dateDialog.value}, modifier = Modifier.align(Alignment.End)) {
                            Text("close")
                        }
                    },
                    onDismissRequest = { dateDialog.value = !dateDialog.value }
                ) {
                    val dateState = rememberDatePickerState(
                        initialDisplayMode = DisplayMode.Picker,
                        initialDisplayedMonthMillis = LocalDateTime.now().monthValue.toLong()
                    )
                    DatePicker(
                        state = dateState,
                        dateValidator = { it ->
                            val instant = Instant.ofEpochMilli(it)
                            true
                        },
                        colors = colors
                    )
                }


            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(50.dp)
                ) {
                    Box(modifier = Modifier.fillMaxWidth(0.5f)) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = genre,
                            onValueChange = { genre = it },
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
                            onDismissRequest = { genreExpand.value = !genreExpand.value }) {
                            DropdownMenuItem(onClick = {
                                genre = "Fiction"
                                genreExpand.value = !genreExpand.value
                            }) {
                                Text(
                                    "Fiction",
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
                            }
                            DropdownMenuItem(onClick = {
                                genre = "Non Fiction"
                                genreExpand.value = !genreExpand.value
                            }) {
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
                            colors = colorText,
                        )
                        DropdownMenu(
                            expanded = subGenreExpand.value,
                            onDismissRequest = { subGenreExpand.value = !subGenreExpand.value }) {

                            if (genre == "Fiction") {
                                fictionSubgenre.forEachIndexed { index, s ->
                                    DropdownMenuItem(onClick = {
                                        subgenre = s
                                        subGenreExpand.value = !subGenreExpand.value
                                    }) {
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
                                }
                            } else if (genre == "Non Fiction") {
                                nonFictionSubgenre.forEachIndexed { index, s ->
                                    DropdownMenuItem(onClick = {
                                        subgenre = s
                                        subGenreExpand.value = !subGenreExpand.value
                                    }) {
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
                                }
                            }


                        }
                    }
                }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
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

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
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

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
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
        }


    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun datePicker(onDismiss: () -> Unit): String {
        val date = remember { mutableStateOf("") }
        val dateState = rememberDatePickerState(
            initialDisplayMode = DisplayMode.Input,
            initialDisplayedMonthMillis = LocalDateTime.now().monthValue.toLong()
        )
        Dialog(onDismissRequest = { onDismiss() }) {
            Card {
                Column {
                    DatePicker(
                        state = dateState,
                        dateValidator = { it ->
                            val instant = Instant.ofEpochMilli(it)
                            true
                        }
                    )
                }
                TextButton(onClick = { onDismiss() }, modifier = Modifier.align(Alignment.End)) {
                    Text("close")
                }
            }
        }

        return ""


    }


}