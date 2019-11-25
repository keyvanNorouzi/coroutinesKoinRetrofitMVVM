package com.keyvan.sample.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keyvan.sample.data.model.Post
import java.lang.ref.WeakReference

abstract class BaseViewModel<N : BaseNavigator> : ViewModel() {

    val loading : MutableLiveData<Post> by lazy {
        MutableLiveData<Post>()
    }

    lateinit var navigator: WeakReference<N>

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

    fun getNavigator(): N? {
        return navigator.get()
    }


}