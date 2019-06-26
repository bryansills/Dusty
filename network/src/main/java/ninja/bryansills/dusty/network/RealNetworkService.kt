package ninja.bryansills.dusty.network

import io.reactivex.Single
import ninja.bryansills.dusty.network.model.PrivateUserResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RealNetworkService(authenticationToken: String) : NetworkService {

    private val spotifyService: SpotifyService

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthenticationInterceptor(authenticationToken))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spotify.com/v1/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        spotifyService = retrofit.create(SpotifyService::class.java)
    }

    override fun getMe(): Single<PrivateUserResponse> = spotifyService.getMe()
}