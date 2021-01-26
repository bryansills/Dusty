package ninja.bryansills.dusty.network.auth

import ninja.bryansills.dusty.network.auth.model.TokenResponse

interface NetworkAuthService {
    suspend fun requestTokens(authorizationCode: String): TokenResponse
}