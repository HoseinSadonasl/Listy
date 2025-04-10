package org.hotaku.listy

import androidx.compose.ui.window.ComposeUIViewController
import org.hotaku.listy.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }