package com.example.aceledacomposeui.model

import java.io.Serializable

class NotificationModel (
    val id : String ?= null,
    val name : String ?= null,
    val date : String ?= null,
    val description : String ?= null
) : Serializable