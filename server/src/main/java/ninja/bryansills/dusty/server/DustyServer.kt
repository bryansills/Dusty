package ninja.bryansills.dusty.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.respondHtml
import io.ktor.http.ContentType
import io.ktor.http.ParametersBuilder
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.response.respondRedirect
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.util.*
import kotlinx.html.*
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
            call.respondText("HELLO BUTTZ FROM BUILT DOCKER!", contentType = ContentType.Text.Plain)
        }
        get("/callback") {
            val queryParameters = call.request.queryParameters
            val authorizationCode = queryParameters.getOrFail("code")
            val tokenResponse = networkAuthService.requestTokens(authorizationCode)
            call.respondHtml {
                head {
                    title { +"Signing in..." }
                    style {
                        unsafe {
                            raw("""
                                body {
                                    height: 100%;
                                    margin: 0;
                                    display: flex;
                                    justify-content: center;
                                    align-items: center;
                                    background-color: #212121;
                                }
                            """.trimIndent())
                        }
                    }
                }
                body {
                    svg {
                        attributes["width"] = "100px"
                        attributes["height"] = "100px"
                        attributes["viewBox"] = "0 0 40 40"
                        unsafe {
                            raw("""
                                <path opacity="0.2" fill="#FFF" d="M20.201,5.169c-8.254,0-14.946,6.692-14.946,14.946c0,8.255,6.692,14.946,14.946,14.946
                                    s14.946-6.691,14.946-14.946C35.146,11.861,28.455,5.169,20.201,5.169z M20.201,31.749c-6.425,0-11.634-5.208-11.634-11.634
                                    c0-6.425,5.209-11.634,11.634-11.634c6.425,0,11.633,5.209,11.633,11.634C31.834,26.541,26.626,31.749,20.201,31.749z"/>
                                <path opacity="0.9" fill="#FFF" d="M26.013,10.047l1.654-2.866c-2.198-1.272-4.743-2.012-7.466-2.012h0v3.312h0
                                    C22.32,8.481,24.301,9.057,26.013,10.047z">
                                    <animateTransform attributeType="xml"
                                        attributeName="transform"
                                        type="rotate"
                                        from="0 20 20"
                                        to="360 20 20"
                                        dur="0.8s"
                                        repeatCount="indefinite"/>
                                </path>
                            """.trimIndent())
                        }
                    }
                    script {
                        unsafe {
                            raw("dusty.passToken(${tokenResponse.accessToken}, ${tokenResponse.refreshToken}, ${tokenResponse.expiresIn});")
                        }
                    }
                }
            }
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