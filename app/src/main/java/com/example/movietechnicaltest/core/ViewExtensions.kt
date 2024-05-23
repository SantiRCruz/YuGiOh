package com.example.movietechnicaltest.core

import android.text.Spanned
import android.view.View
import androidx.core.text.HtmlCompat

fun View.hide() {
    this.visibility = View.GONE
}
fun View.show() {
    this.visibility = View.VISIBLE
}

fun String.htmlFormat(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}