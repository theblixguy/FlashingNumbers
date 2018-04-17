package com.suyashsrijan.flashingnumbers.main.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.text.Editable
import android.view.View
import com.suyashsrijan.flashingnumbers.app.repository.NumbersRepositoryImpl
import com.suyashsrijan.flashingnumbers.main.extension.toTrimmedFloat
import com.suyashsrijan.flashingnumbers.main.misc.coroutineAnimation
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class MainViewModel(private val repository: NumbersRepositoryImpl) : ViewModel() {

    /* This variable stores the sum of all the values in String format */
    var sumOfNumbers: ObservableField<String> = ObservableField("0.0")

    /* This variable stores whether the sum is currently flashing or not */
    var flashSum: ObservableField<Boolean> = ObservableField(false)

    /* This variable stores whether the sum will be flashed using the binding adapter or using
    * coroutines. If set to true, then the view will flash using the binding adapter, otherwise it
    * will flash using couroutines. This is for the bonus points!
    */
    private var flashSumUsingBindingAdapter: Boolean = false

    /* A Job object which is a reference to the coroutine executing the flashing animation */
    private lateinit var animationJob: Job

    /* Class initializer */
    init {
        initViewModel()
    }

    /* Initialize the view model repository with default values and update the sum */
    private fun initViewModel() {
        repository.appendNumbers(listOf(1f, 2f, 3f, 4f, 5f, 6f))
        updateSum()
    }

    /* Update the sum of all values */
    private fun updateSum() {
        sumOfNumbers.set(repository.getSumOfAllNumbers().toString().toTrimmedFloat(2))
    }

    /* Update the number at a specified index in the repository and tell the view that the sum has
    *  changed. If the number is null or empty, then use the default value to update the number at
    *  the specified index
    */
    fun updateNumber(index: Int, numberAsEditable: Editable, defaultNumber: String) {
        require(index >= 0 && index < repository.getAllNumbers().size) {
            "$index is an invalid index. Index must be a non-negative integer, between 0 " +
                    "and ${repository.getAllNumbers().size - 1}."
        }

        require(defaultNumber.toFloatOrNull() != null) {
            "$defaultNumber.toString() is an invalid number. You must pass a valid floating " +
                    "point number to be used as a default."
        }

        if (numberAsEditable.toString().isNotEmpty()) {
            updateNumber(index, numberAsEditable.toString())
        } else {
            updateNumber(index, defaultNumber)
        }
    }

    /* Helper function to update number in repository (also used later in tests) */
    fun updateNumber(index: Int, number: String) {
        repository.updateNumber(index, number.toFloat())
        updateSum()
    }

    /* Return the number located at a specified index in the repository */
    fun getNumberForIndex(index: Int): Float {
        require(index >= 0 && index < repository.getAllNumbers().size) {
            "$index is an invalid index. Index must be a non-negative integer, between 0 " +
                    "and ${repository.getAllNumbers().size - 1}"
        }

        return repository.getNumber(index)
    }

    /* Start flashing the sum if it's not flashing, otherwise stop flashing */
    fun toggleFlashing(view: View) {
        if (flashSumUsingBindingAdapter) {
            flashSum.set(flashSum.get()?.not())
        } else {
            if (flashSum.get() == false) {
                flashSum.set(flashSum.get()?.not())
                animationJob = launch(UI) {
                    while (true) {
                        // TODO: Use UI#awaitFrame instead of delay
                        coroutineAnimation(view, 1f, 0f, 500) { v, value ->
                            v.alpha = value
                        }
                        delay(500)
                        coroutineAnimation(view, 0f, 1f, 500) { v, value ->
                            v.alpha = value
                        }
                        delay(500)
                    }
                }
            } else {
                flashSum.set(flashSum.get()?.not())
                animationJob.cancel()
                view.alpha = 1f
            }
        }
    }
}