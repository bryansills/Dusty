package ninja.bryansills.dusty.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(val authenticationToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $authenticationToken")
            .build()
        return chain.proceed(request)
    }
}