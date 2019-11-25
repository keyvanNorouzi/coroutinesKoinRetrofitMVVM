package com.keyvan.sample.data.model

import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class Post(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String?

)
