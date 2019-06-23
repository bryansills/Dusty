package ninja.bryansills.dusty.network

import io.reactivex.Single
import ninja.bryansills.dusty.network.model.PrivateUserResponse

interface NetworkService {
    fun getMe(): Single<PrivateUserResponse>
}