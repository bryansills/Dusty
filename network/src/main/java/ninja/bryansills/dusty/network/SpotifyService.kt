package ninja.bryansills.dusty.network

import ninja.bryansills.dusty.network.model.PrivateUserResponse
import ninja.bryansills.dusty.network.model.RecentlyPlayedResponse
import retrofit2.http.GET

interface SpotifyService {
    @GET("/v1/me") suspend fun getMe(): PrivateUserResponse

    @GET("/v1/me/player/recently-played") suspend fun getRecentlyPlayed(): RecentlyPlayedResponse
}