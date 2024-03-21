package dev.tilbrook.feature

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class LoginScreen(
    semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeScreen<LoginScreen>(
    semanticsProvider,
    viewBuilderAction = { hasTestTag("LoginScreen") }
) {
    val username: KNode = child { hasTestTag("username") }
    val password: KNode = child { hasTestTag("password") }
    fun enterPassword(value: String) {
        password.performTextReplacement(value)
    }
    val login: KNode = child { hasTestTag("login") }
}

