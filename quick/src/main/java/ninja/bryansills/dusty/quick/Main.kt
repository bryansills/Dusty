package ninja.bryansills.dusty.quick

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import ninja.bryansills.dusty.network.RealNetworkService
import ninja.bryansills.dusty.network.auth.RealNetworkAuthService

fun main(args: Array<String>) {
    val networkAuthService = RealNetworkAuthService(args[1], args[2], args[3])

    println("yo1")
    runBlocking {
        println("yo2")
        withTimeout(10000) {
            println("yo3")
            networkAuthService.requestTokens(args[4])
            println("yo4")
        }
        println("yo5")
    }
    println("yo6")

    val networkService = RealNetworkService(args[0])
    val response = networkService.getRecentlyPlayed().blockingGet()
    println(response.toString())
}