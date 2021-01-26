package ninja.bryansills.dusty.network.auth

import ninja.bryansills.dusty.network.auth.model.TokenResponse
import retrofit2.http.POST

interface SpotifyAuthService {
    @POST("/api/token") suspend fun requestTokens(grantType: String, code: String, redirectUri: String): TokenResponse
}