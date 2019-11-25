package com.keyvan.sample.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.keyvan.sample.R
import com.keyvan.sample.ui.base.BaseActivity


class MainActivity : BaseActivity<MainNavigator, MainViewModel>(),
    MainNavigator {
    override fun getViewModels(): MainViewModel? {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel!!.setNavigator(this)
        return viewModel
    }

    override fun showData() {
    }


    lateinit var sample_TextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sample_TextView = findViewById(R.id.textView_sample)

        viewModel!!.post.observe(this, androidx.lifecycle.Observer {
            this.sample_TextView.text = String.format("title = %s", it.title)

        })



        viewModel!!.errorMessage.observe(this, androidx.lifecycle.Observer {
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        })





    }
}
