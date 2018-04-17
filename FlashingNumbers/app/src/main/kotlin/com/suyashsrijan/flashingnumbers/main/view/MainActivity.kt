package com.suyashsrijan.flashingnumbers.main.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.suyashsrijan.flashingnumbers.R
import com.suyashsrijan.flashingnumbers.databinding.ActivityMainBinding
import com.suyashsrijan.flashingnumbers.main.misc.contentView
import com.suyashsrijan.flashingnumbers.main.viewmodel.MainViewModel
import com.suyashsrijan.flashingnumbers.main.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private val binding: ActivityMainBinding by contentView(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }
}
