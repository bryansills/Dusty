package ninja.bryansills.dusty.server

import io.ktor.application.*
import io.ktor.features.*
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
    install(CallLogging)

    val networkAuthService: NetworkAuthService = RealNetworkAuthService(
        BuildConfig.CLIENT_ID,
        BuildConfig.CLIENT_SECRET,
        BuildConfig.CALLBACK_URL
    )

    routing {
        get("/") {
            log.info("BLARG ROOT")
            call.respondText("HELLO BUTTZ FROM BUILT DOCKER!", contentType = ContentType.Text.Plain)
        }
        get("/callback") {
            val queryParameters = call.request.queryParameters
            log.info("BLARG QUERY PARAMS ${queryParameters.toMap()}")
            val authorizationCode = queryParameters.getOrFail("code")
            log.info("BLARG AUTH CODE $authorizationCode")
            val tokenResponse = networkAuthService.requestTokens(authorizationCode)
            log.info("BLARG TOKEN RESPONSE $tokenResponse")
            call.respondText(tokenResponse.toString(), ContentType.Text.Plain)
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