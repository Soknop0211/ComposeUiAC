package com.example.aceledacomposeui.model

import java.io.Serializable

data class User(
    val id : String ?= null,
    val name : String ?= null,
    val profileImage : String ?= null,
    val phone : String ?= null
) : Serializable
