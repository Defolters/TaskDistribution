package io.github.defolters.taskdistribution.util

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T : Any?> Call<T>.enqueue(onSuccess: (T?) -> Unit, onError: (String?) -> Unit = {}) =
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                onSuccess(response.body())
            } else {
                onError(response.errorBody()?.string())
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            onError(t.message)
        }
    })