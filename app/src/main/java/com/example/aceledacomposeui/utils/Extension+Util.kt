package com.example.aceledacomposeui.utils

import android.util.Log
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