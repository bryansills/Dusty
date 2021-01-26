package ninja.bryansills.dusty.network

import io.reactivex.Single
import ninja.bryansills.dusty.network.model.PrivateUserResponse
import ninja.bryansills.dusty.network.model.RecentlyPlayedResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RealNetworkService(accessToken: String) : NetworkService {

    private val spotifyService: SpotifyService

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AccessTokenInterceptor(accessToken))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spotify.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        spotifyService = retrofit.create(SpotifyService::class.java)
    }

    override fun getMe(): Single<PrivateUserResponse> = spotifyService.getMe()
    override fun getRecentlyPlayed(): Single<RecentlyPlayedResponse> = spotifyService.getRecentlyPlayed()
}