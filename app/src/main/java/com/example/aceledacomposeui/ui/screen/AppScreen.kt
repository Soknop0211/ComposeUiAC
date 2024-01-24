package com.example.aceledacomposeui.ui.screen

sealed class AppScreen(val route: String) {
    object HomeScreen : AppScreen(ConstantAppScreenName.HomeScreen)
    object SeeMoreScreen : AppScreen(ConstantAppScreenName.SeeMoreScreen)
    object NotificationScreen : AppScreen(ConstantAppScreenName.NotificationScreen)

    object KhQrCodeScreen : AppScreen(ConstantAppScreenName.KhQrCodeScreen)

    object ProfileScreen : AppScreen(ConstantAppScreenName.ProfileScreen)

    object HomeNewScreen : AppScreen(ConstantAppScreenName.HomeNewScreen)

    object UpdateScreen : AppScreen(ConstantAppScreenName.UpdateScreen + "/{id}") {
        fun getItemId(mId : String) : String = "${ConstantAppScreenName.UpdateScreen}/$mId"
    }
}

object ConstantAppScreenName {
    const val HomeNewScreen = "home_new_screen"
    const val HomeScreen = "home_screen"
    const val SeeMoreScreen = "see_more_screen"
    const val UpdateScreen = "update_screen"
    const val NotificationScreen = "notification_screen"
    const val ProfileScreen = "profile_screen"
    const val KhQrCodeScreen = "kh_qrcode_screen"
}