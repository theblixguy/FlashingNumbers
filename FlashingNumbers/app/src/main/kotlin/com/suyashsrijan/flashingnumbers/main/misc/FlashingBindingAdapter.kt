package com.suyashsrijan.flashingnumbers.main.misc

import android.content.Context
import android.databinding.BindingAdapter
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.suyashsrijan.flashingnumbers.R

/* Binding adapter for the view that controls the view's flashing */
@BindingAdapter("isFlashing")
fun setIsFlashing(view: View, isFlashing: Boolean) {
    if (isFlashing) {
        view.animation ?: run {
            view.startAnimation(getFlashingAnimation(view.context))
        }
    } else {
        view.animation?.cancel()
        view.animation = null
    }
}

/* Helper function to return the flashing animation from XML */
private fun getFlashingAnimation(context: Context): Animation {
    return AnimationUtils.loadAnimation(context, R.anim.flashing_view_anim)
}