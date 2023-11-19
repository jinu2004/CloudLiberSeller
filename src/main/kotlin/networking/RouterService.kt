package networking

import io.ktor.client.*
import io.ktor.client.engine.apache5.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import networking.dataclass.BookFullDetails
import networking.dataclass.BookPreview

interface RouterService {
    suspend fun getData():List<BookPreview>
    suspend fun uploadImageToGoogleStorage(imageByte:ByteArray)
    suspend fun uploadDetails(fullDetails: BookFullDetails)

    companion object{
        fun create():ApiService{
            return ApiService(client = HttpClient(Apache5){
                install(Logging) {
                    logger = Logger.SIMPLE
                    level = LogLevel.ALL
                }
                install(ContentNegotiation){
                    json()
                }
            })
        }
    }
}