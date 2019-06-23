package ninja.bryansills.dusty.quick

import ninja.bryansills.dusty.network.RealNetworkService

fun main() {
    val networkService = RealNetworkService()
    val response = networkService.getMe().blockingGet()
    println(response.toString())
}