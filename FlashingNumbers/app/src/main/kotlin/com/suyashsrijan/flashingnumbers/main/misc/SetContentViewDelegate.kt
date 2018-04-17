package com.suyashsrijan.flashingnumbers.main.misc

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/* A delegate to simplify view data binding on Activity */
class SetContentView<in A : Activity, out V : ViewDataBinding>(@LayoutRes private val layoutRes: Int) : ReadOnlyProperty<A, V> {
    private var value: V? = null

    override fun getValue(thisRef: A, property: KProperty<*>): V {
        value = value ?: DataBindingUtil.setContentView(thisRef, layoutRes)
        return value!!
    }
}

/* Helper function to simplify SetContentView */
fun <A : Activity, V : ViewDataBinding> contentView(@LayoutRes layoutRes: Int): SetContentView<A, V> {
    return SetContentView(layoutRes)
}