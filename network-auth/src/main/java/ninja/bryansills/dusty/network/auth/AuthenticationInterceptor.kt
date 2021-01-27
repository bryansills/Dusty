package ninja.bryansills.dusty.network.auth

import android.util.base64Encoded
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(clientId: String, clientSecret: String) : Interceptor {
    private val authenticationToken = "$clientId:$clientSecret".base64Encoded

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Basic $authenticationToken")
            .build()
        return chain.proceed(request)
    }
}