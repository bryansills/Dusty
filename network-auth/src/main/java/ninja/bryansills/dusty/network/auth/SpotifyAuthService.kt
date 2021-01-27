package ninja.bryansills.dusty.network.auth

import ninja.bryansills.dusty.network.auth.model.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SpotifyAuthService {
    @FormUrlEncoded
    @POST("/api/token")
    suspend fun requestTokens(
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String
    ): TokenResponse
}