package database

import Entity.BookPreview
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.cloudliber.Cloud_Liber_Seller
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class BookDataSource(db: Cloud_Liber_Seller):BookDataEntity {

    private val queries = db.bookPreviewQueries
    override suspend fun selectAll(): Flow<List<BookPreview>> {
        return withContext(Dispatchers.IO){
            queries.selectAll().asFlow().mapToList(coroutineContext)
        }
    }

    override suspend fun insertBook(coverpage: String, price: String, rating: String, title: String, subgenre: String) {
        withContext(Dispatchers.IO){
            queries.insert(coverpage, price, rating, title, subgenre)
        }
    }

}