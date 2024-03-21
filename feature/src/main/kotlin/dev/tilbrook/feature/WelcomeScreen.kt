package dev.tilbrook.feature

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(
    onLogin: () -> Unit,
) {
    Scaffold(modifier = Modifier.testTag("WelcomeScreen")) {
        Column(
            modifier = Modifier.padding(it).fillMaxSize(),
            verticalArrangement = spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = onLogin, modifier = Modifier.testTag("login")) {
                Text(text = "Login")
            }
        }
    }
}