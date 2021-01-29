package ninja.bryansills.dusty.network

import ninja.bryansills.dusty.network.model.PrivateUserResponse
import ninja.bryansills.dusty.network.model.RecentlyPlayedResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
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
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        spotifyService = retrofit.create(SpotifyService::class.java)
    }

    override suspend fun getMe(): PrivateUserResponse = spotifyService.getMe()

    override suspend fun getRecentlyPlayed(): RecentlyPlayedResponse = spotifyService.getRecentlyPlayed()
}