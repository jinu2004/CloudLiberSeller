package networking.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class BookFullDetails(
    val about: String,//
    val author: String,//
    val coverPage: String,
    val publishedDate: String,
    val genre: String,//
    val isbn: String,
    val price: String,
    val publisher: String,//
    val rating: String,
    val subgenre: String,
    val bookName: String//
)
