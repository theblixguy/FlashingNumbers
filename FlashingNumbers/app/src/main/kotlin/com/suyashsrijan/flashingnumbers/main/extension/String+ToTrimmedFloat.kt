package com.suyashsrijan.flashingnumbers.main.extension

import java.util.*

/* Return the String's float representation trimmed down to a specified number of decimals */
fun String.toTrimmedFloat(decimals: Int): String {
    return String.format(Locale.UK, "%.${decimals}f", toFloat())
}