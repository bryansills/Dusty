package ninja.bryansills.dusty.quick

import kotlinx.coroutines.runBlocking
import ninja.bryansills.dusty.network.RealNetworkService
import ninja.bryansills.dusty.network.auth.RealNetworkAuthService

fun main(args: Array<String>) {
    println(args.toList().toString())
    val networkAuthService = RealNetworkAuthService(args[0], args[1], args[2])

    runBlocking {
        val tokenResponse = networkAuthService.requestTokens(args[3])
        println(tokenResponse)
        val networkService = RealNetworkService(tokenResponse.accessToken)
        val meResponse = networkService.getMe()
        println(meResponse)
    }
}