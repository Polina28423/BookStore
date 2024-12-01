package com.example.bookstore.main_screen.bottom_menu

import com.example.bookstore.R


sealed class BottomMenuItem(
    val route: String,
    val title: String,
    val iconId: Int
) {

    object Home : BottomMenuItem(
        route = "",
        title = "",
        iconId = R.drawable.ic_home
    )
    object Favs : BottomMenuItem(
        route = "",
        title = "Favs",
        iconId = R.drawable.ic_favs
    )
    object Settings : BottomMenuItem(
        route = "",
        title = "Settings",
        iconId = R.drawable.ic_settings
    )
}
