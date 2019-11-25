package com.keyvan.sample.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T : BaseNavigator, V : BaseViewModel<T>> : AppCompatActivity(), BaseNavigator {

    var viewModel: V? = null
    abstract fun getViewModels(): V?

    override fun showLoading() {

    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        if (this.viewModel == null) this.viewModel = getViewModels()
    }


}