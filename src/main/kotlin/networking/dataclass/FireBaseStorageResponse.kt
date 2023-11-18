package networking.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class FireBaseStorageResponse(
    val bucket: String,
    val contentDisposition: String,
    val contentEncoding: String,
    val contentType: String,
    val crc32c: String,
    val downloadTokens: String,
    val etag: String,
    val generation: String,
    val md5Hash: String,
    val metageneration: String,
    val name: String,
    val size: String,
    val storageClass: String,
    val timeCreated: String,
    val updated: String
)