package com.rmaprojects.jetnotes.navigation

sealed class Screen(val route: String) {
    object Home: Screen("Home")
    object SearchNote: Screen("SearchNote")
    object Settings: Screen("Settings")
    object About: Screen("About")
    object InsertEditNote: Screen("InsertEditNote?noteId={noteId}") {
        fun createRoute(noteId: Int): String {
            return "InsertEditNote?noteId=$noteId"
        }
    }
}