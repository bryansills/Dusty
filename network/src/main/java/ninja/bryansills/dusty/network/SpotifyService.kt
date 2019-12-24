package ninja.bryansills.dusty.network

import io.reactivex.Single
import ninja.bryansills.dusty.network.model.PrivateUserResponse
import ninja.bryansills.dusty.network.model.RecentlyPlayedResponse
import retrofit2.http.GET

interface SpotifyService {
    @GET("/v1/me") fun getMe(): Single<PrivateUserResponse>

    @GET("/v1/me/player/recently-played") fun getRecentlyPlayed(): Single<RecentlyPlayedResponse>
}