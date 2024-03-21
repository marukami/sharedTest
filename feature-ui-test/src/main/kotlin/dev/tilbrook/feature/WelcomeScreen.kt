package dev.tilbrook.feature

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class WelcomeScreen(
    semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeScreen<WelcomeScreen>(
    semanticsProvider,
    viewBuilderAction = { hasTestTag("WelcomeScreen") }
) {
    val login: KNode = child { hasTestTag("login") }
}

