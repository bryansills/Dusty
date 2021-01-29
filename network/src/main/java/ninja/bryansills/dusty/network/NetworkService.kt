package ninja.bryansills.dusty.network

import ninja.bryansills.dusty.network.model.PrivateUserResponse
import ninja.bryansills.dusty.network.model.RecentlyPlayedResponse

interface NetworkService {
    suspend fun getMe(): PrivateUserResponse
    suspend fun getRecentlyPlayed(): RecentlyPlayedResponse
}