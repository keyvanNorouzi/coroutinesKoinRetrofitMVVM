package com.keyvan.sample.data.remote

import android.util.Log
import com.keyvan.sample.data.model.Post
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper {

    val okHttpClient = OkHttpClient().newBuilder().addInterceptor(getInterceptor()).build()
    lateinit var apiService: ApiService

    init {
        makeService()
    }

    private fun makeService() {
        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getTodoRequest(id: Int): Result<Post> {
        return safeApiCall(call = { apiService.getTodo(id) })
    }


    private fun getInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }


    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        return try {
            val myResp = call.invoke()
            if (myResp.isSuccessful) {
                Result.Success(myResp.body()!!)
            } else {

                /*
                handle standard error codes
                if (myResp.code() == 403){
                    Log.i("responseCode","Authentication failed")
                }
                .
                .
                .
                 */

                Result.Error(myResp.errorBody()?.string() ?: "Something goes wrong")
            }

        } catch (e: Exception) {
            Result.Error(e.message ?: "Internet error runs")
        }
    }


}