package networking

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import networking.dataclass.BookPreview

class ApiService(private val client: HttpClient) : RouterService {
    override suspend fun getData(): List<BookPreview> {
        return try {
            val response = client.get(ApiRouter.POSTS) {
                header("API-KEY", ApiRouter.APIKEY)
                parameter("API-KEY", ApiRouter.APIKEY)
            }
            return response.body<List<BookPreview>>()

        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

}