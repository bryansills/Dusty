package ninja.bryansills.dusty.quick

import ninja.bryansills.dusty.network.RealNetworkService

fun main(args: Array<String>) {
    val networkService = RealNetworkService(args[0])
    val response = networkService.getRecentlyPlayed().blockingGet()
    println(response.toString())
}