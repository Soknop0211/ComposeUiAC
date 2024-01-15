package com.example.aceledacomposeui.model

import android.graphics.Bitmap
import java.io.Serializable

data class User(
    val id : String ?= null,
    val name : String ?= null,
    var profileImageBitmap : Bitmap ?= null,
    val phone : String ?= null
) : Serializable
