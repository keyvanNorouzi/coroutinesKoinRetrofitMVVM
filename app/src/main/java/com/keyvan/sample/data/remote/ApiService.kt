package com.keyvan.sample.data.remote

import com.keyvan.sample.data.model.Todo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/posts/{id}")
    suspend fun getTodo(@Path("id") id: Int): Response<Todo>
}