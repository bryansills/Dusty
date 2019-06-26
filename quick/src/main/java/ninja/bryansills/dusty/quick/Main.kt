package ninja.bryansills.dusty.quick

import ninja.bryansills.dusty.network.RealNetworkService

fun main() {
    val networkService = RealNetworkService("lol todo: fix")
    val response = networkService.getMe().blockingGet()
    println(response.toString())
}