package networking

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import networking.dataclass.BookFullDetails
import networking.dataclass.BookPreview
import networking.dataclass.FireBaseStorageResponse

class ApiService(private val client: HttpClient) : RouterService {
    override suspend fun getData(): List<BookPreview> {
        return try {
            val response = client.get(ApiRouter.GET) {
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

    override suspend fun uploadImageToGoogleStorage(imageByte: ByteArray) {
        try {
            val fileName = "books/image_test_2.jpg"
            val apiUrl = ApiRouter.FIREBASE_STORAGE+fileName
            val response = client.post(apiUrl){
                headers{
                    append("Content-Type","application/octet-stream")
                    append("Authorization", "Bearer ${ApiRouter.APPID}")
                }
                setBody(imageByte)
            }
            println(response.body<FireBaseStorageResponse>().downloadTokens)

        }
        catch (e:Exception){
            return
        }


    }

    override suspend fun uploadDetails(fullDetails: BookFullDetails) {

        try {
            val response = client.post(ApiRouter.POST){
                header("API-KEY", ApiRouter.APIKEY)
                parameter("API-KEY", ApiRouter.APIKEY)
                setBody(fullDetails)
            }

            println(response.status.value)
        }
        catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            return
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            return
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            return
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return
        }

    }


}