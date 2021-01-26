package ninja.bryansills.dusty.server

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.http.ParametersBuilder
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.response.respondRedirect
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.util.*
import ninja.bryansills.dusty.network.auth.NetworkAuthService
import ninja.bryansills.dusty.network.auth.RealNetworkAuthService

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(DefaultHeaders)
    val networkAuthService: NetworkAuthService = RealNetworkAuthService(
        BuildConfig.CALLBACK_URL,
        BuildConfig.CLIENT_ID,
        BuildConfig.CLIENT_SECRET
    )

    routing {
        get("/") {
            call.respondText("HELLO BUTTZ FROM BUILT DOCKER!", contentType = ContentType.Text.Plain)
        }
        get("/callback") {
            val queryParameters = call.request.queryParameters
            val authorizationCode = queryParameters.getOrFail("code")
            val tokenResponse = networkAuthService.requestTokens(authorizationCode)
            call.respondText(tokenResponse.toString(), ContentType.Text.Html)
        }
        get("/start") {
            val uriBuilder = URLBuilder(
                protocol = URLProtocol.HTTPS,
                host = "accounts.spotify.com",
                encodedPath = "/authorize",
                parameters = ParametersBuilder().apply {
                    append("client_id", BuildConfig.CLIENT_ID)
                    append("response_type", "code")
                    append("redirect_uri", BuildConfig.CALLBACK_URL)
                    append("scope", "user-read-private")
                }
            )
            call.respondRedirect(uriBuilder.build().toString())
        }
    }
}