package ninja.bryansills.dusty.network.auth

import ninja.bryansills.dusty.network.auth.model.TokenResponse
import ninja.bryansills.dusty.network.core.AuthenticationInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val GRANT_TYPE = "authorization_code"

class RealNetworkAuthService(
    clientId: String,
    clientSecret: String,
    private val redirectUri: String
) : NetworkAuthService {

    private val spotifyAuthService: SpotifyAuthService

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthenticationInterceptor(clientId, clientSecret))
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://accounts.spotify.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        spotifyAuthService = retrofit.create(SpotifyAuthService::class.java)
    }

    override suspend fun requestTokens(authorizationCode: String): TokenResponse {
        return spotifyAuthService.requestTokens(GRANT_TYPE, authorizationCode, redirectUri)
    }
}