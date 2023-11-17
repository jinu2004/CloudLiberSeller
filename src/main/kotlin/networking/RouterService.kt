package networking

import io.ktor.client.*
import io.ktor.client.engine.apache5.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import networking.dataclass.BookPreview

interface RouterService {
    suspend fun getData():List<BookPreview>
    companion object{
        fun create():ApiService{
            return ApiService(client = HttpClient(Apache5){
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(ContentNegotiation){
                    json()
                }
            })
        }
    }
}