package io.github.defolters.taskdistribution.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

fun View.showKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    inputMethodManager.showSoftInput(this, 0)
}

fun View.getLayoutInflater(): LayoutInflater {
    return LayoutInflater.from(context)
}