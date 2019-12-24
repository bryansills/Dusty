package ninja.bryansills.dusty.network.model.recent

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecentlyPlayedCursors(
    val before: String,
    val after: String
)
