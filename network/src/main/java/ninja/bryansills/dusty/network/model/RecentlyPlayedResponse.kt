package ninja.bryansills.dusty.network.model

import com.squareup.moshi.JsonClass
import ninja.bryansills.dusty.network.model.recent.RecentlyPlayedCursors

@JsonClass(generateAdapter = true)
data class RecentlyPlayedResponse(
    val next: String,
    val cursors: RecentlyPlayedCursors,
    val limit: Int
)

