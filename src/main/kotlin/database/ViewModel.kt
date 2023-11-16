package database

import Entity.BookPreview
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch


class ViewModel(private val bookDataSource: BookDataSource):ViewModel() {
    var listOfBook = flowOf<List<BookPreview>>()
    fun getAll(){
        viewModelScope.launch {
           listOfBook = bookDataSource.selectAll()
        }
    }

    fun insetBook(coverpage: String, price: String, rating: String, title: String, subgenre: String)
    {
        viewModelScope.launch {
            bookDataSource.insertBook(coverpage, price, rating, title, subgenre)
        }

    }
}