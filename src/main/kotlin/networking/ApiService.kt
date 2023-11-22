package networking

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import networking.dataclass.BookFullDetails
import networking.dataclass.BookPreview
import networking.dataclass.FireBaseStorageResponse
import org.apache.hc.client5.http.classic.HttpClient

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
            val apiUrl = ApiRouter.FIREBASE_STORAGE + fileName
            val response = client.post(apiUrl) {
                headers {
                    append("Content-Type", "application/octet-stream")
                    append("Authorization", "Bearer ${ApiRouter.APPID}")
                }
                setBody(imageByte)
            }
            println(response.body<FireBaseStorageResponse>().downloadTokens)

        } catch (e: Exception) {
            return
        }


    }

    override suspend fun uploadDetails(fullDetails: BookFullDetails, imageByte: ByteArray) {
        try {
            val fileName = "books/${fullDetails.bookName}"
            val apiUrl = ApiRouter.FIREBASE_STORAGE + fileName
            val response = client.post(apiUrl) {
                headers {
                    append("Content-Type", "application/octet-stream")
                    append("Authorization", "Bearer ${ApiRouter.APPID}")
                }
                setBody(imageByte)
            }
            println(response.body<FireBaseStorageResponse>().downloadTokens)
            val downloadToken = response.body<FireBaseStorageResponse>().downloadTokens
            val filepath = response.body<FireBaseStorageResponse>().name

            if (response.status.value == 200) {
                fullDetails.coverPage =
                    "https://firebasestorage.googleapis.com/v0/b/${ApiRouter.BUCKETNAME}/o/$filepath?alt=media&token=$downloadToken"

                try {
                    val response2 = client.post(ApiRouter.POST) {
                        header("API-KEY", ApiRouter.POST_APIKEY)
                        header("Content-Type", "application/json")
                        setBody(fullDetails)
                    }

                    println(response2.status.value)
                } catch (e: RedirectResponseException) {
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

        } catch (e: Exception) {
            return
        }

    }


}