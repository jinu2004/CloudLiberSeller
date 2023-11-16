package database

import Entity.BookPreview
import kotlinx.coroutines.flow.Flow

interface BookDataEntity {
    suspend fun selectAll(): Flow<List<BookPreview>>
    suspend fun insertBook(
        coverpage: String,
        price: String,
        rating: String,
        title: String,
        subgenre: String,
    )
}