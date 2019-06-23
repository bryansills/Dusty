package ninja.bryansills.dusty.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrivateUserResponse(
    val birthdate: String,
    val country: String,
    val email: String,
    val product: String
)