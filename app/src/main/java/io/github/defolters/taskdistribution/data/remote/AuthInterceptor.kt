package io.github.defolters.taskdistribution.data.remote

import android.util.Log
import io.paperdb.Paper
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        var res = chain.proceed(req.applyAuthHeader())
        if (res.code == 401 || res.code == 403) {
            // navigateToLogin()
            // clean token
            Log.d("Authorization", "code: ${res.code}")
        }
        return res
    }

    private fun Request.applyAuthHeader(): Request {
        val bearer = Paper.book().read<String?>("TOKEN")

        Log.d("Authorization", " $bearer")

        return newBuilder().apply {
            addHeader("Accept", "application/json")
            if (!bearer.isNullOrBlank()) {
                addHeader("Authorization", "Bearer $bearer")
            }
        }.build()
    }
}