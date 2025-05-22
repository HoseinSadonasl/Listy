package org.hotaku.listy

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.hotaku.listy.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            alwaysOnTop = true,
            title = "Listy",
        ) {
            App()
        }
    }
}