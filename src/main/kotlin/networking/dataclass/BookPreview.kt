package networking.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class BookPreview(
    val coverpage: String,
    val price: String,
    val rating: String,
    val title: String,
    val subgenre:String
)