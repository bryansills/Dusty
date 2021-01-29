package ninja.bryansills.dusty.quick

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import ninja.bryansills.dusty.network.RealNetworkService
import ninja.bryansills.dusty.network.auth.RealNetworkAuthService

fun main(args: Array<String>) {
    println(args.toList().toString())
    val networkAuthService = RealNetworkAuthService(args[0], args[1], args[2])

    runBlocking {
        val tokenResponse = networkAuthService.requestTokens(args[3])
        val networkService = RealNetworkService(tokenResponse.accessToken)
        val response = networkService.getRecentlyPlayed().blockingGet()
        println(response.toString())
    }
}