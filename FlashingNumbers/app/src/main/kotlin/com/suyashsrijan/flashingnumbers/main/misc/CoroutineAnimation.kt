package com.suyashsrijan.flashingnumbers.main.misc

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.FloatEvaluator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.LinearInterpolator
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.suspendCoroutine

/* Perform an animation on the view using coroutines */
fun coroutineAnimation(view: View, from: Float, to: Float, duration: Long = 500, valueListener: (View, Float) -> Unit) = launch(UI) {
    suspendCoroutine { continuation ->
        view.post {
            ValueAnimator.ofFloat(from, to).apply {
                addUpdateListener { valueAnimator ->
                    valueListener.invoke(view, valueAnimator.animatedValue as Float)
                }
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        continuation.resume(Unit)
                    }
                })
                this.setEvaluator(FloatEvaluator())
                this.interpolator = LinearInterpolator()
                this.duration = duration
                this.startDelay = 0
                start()
            }
        }
    }
}