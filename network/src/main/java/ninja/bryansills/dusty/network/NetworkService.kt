package ninja.bryansills.dusty.network

import io.reactivex.Single
import ninja.bryansills.dusty.network.model.PrivateUserResponse
import ninja.bryansills.dusty.network.model.RecentlyPlayedResponse

interface NetworkService {
    fun getMe(): Single<PrivateUserResponse>
    fun getRecentlyPlayed(): Single<RecentlyPlayedResponse>
}