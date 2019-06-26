package ninja.bryansills.dusty.network

import io.reactivex.Single
import ninja.bryansills.dusty.network.model.PrivateUserResponse
import retrofit2.http.GET

interface SpotifyService {
    @GET("me") fun getMe(): Single<PrivateUserResponse>
}