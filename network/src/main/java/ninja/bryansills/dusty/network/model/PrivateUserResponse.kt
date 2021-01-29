package ninja.bryansills.dusty.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrivateUserResponse(
    val id: String,
    @Json(name = "display_name") val displayName: String,
    val product: String
)