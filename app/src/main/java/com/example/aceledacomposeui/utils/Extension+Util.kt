package com.example.aceledacomposeui.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.aceledacomposeui.R
import org.burnoutcrew.reorderable.BuildConfig

val fontFamily = FontFamily(Font(R.font.lexend_deca_regular_title))

fun String.logDebug(key : String ?= null) {
    if (BuildConfig.DEBUG) {
        Log.d(key ?: "ComposeLogDebug", this)
    }
}

@SuppressLint("DiscouragedApi")
fun Context.fileNameToDrawer(imageName : String) : Int{
    return this.resources.getIdentifier(imageName, "drawable", this.packageName)
}

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = this.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

