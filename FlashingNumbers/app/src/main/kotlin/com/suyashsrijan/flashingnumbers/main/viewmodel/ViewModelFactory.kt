package com.suyashsrijan.flashingnumbers.main.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject

/* Helper function to create a view model factory */
class ViewModelFactory<M : ViewModel> @Inject constructor(private val viewModel: Lazy<M>) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <M : ViewModel?> create(modelClass: Class<M>): M = viewModel.get() as M
}