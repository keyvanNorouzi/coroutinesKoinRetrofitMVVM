package com.keyvan.sample.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.keyvan.sample.data.model.Post

import com.keyvan.sample.data.remote.ApiHelper
import com.keyvan.sample.data.remote.Result
import com.keyvan.sample.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel<MainNavigator>() {
    val post: MutableLiveData<Post> by lazy {
        MutableLiveData<Post>()
    }

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getTodoFromServer() {
        viewModelScope.launch {
            val retrofitPost = ApiHelper.getTodoRequest(1)
            when (retrofitPost) {
                is Result.Success -> { post.postValue(retrofitPost.data)}
                is Result.Error -> {
                    errorMessage.postValue(retrofitPost.exception)
                }
            }
        }

    }

}