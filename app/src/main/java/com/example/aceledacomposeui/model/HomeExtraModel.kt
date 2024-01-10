package com.example.aceledacomposeui.model

import java.io.Serializable

data class HomeExtraModel(
    val id : String,
    val name : String,
    val description : String,
    val isExchangeRate : Boolean,
    val logo : Int
) : Serializable