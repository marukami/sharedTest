@file:OptIn(ExperimentalMaterial3Api::class)

package dev.tilbrook.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LoginScreen(
    onLogin: () -> Unit,
) {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val isValid = remember(username, password) {
        username.isNotBlank() && !password.contains(Regex("\\s")) && password.length > 8
    }
    Scaffold(
        modifier = Modifier.testTag("LoginScreen"),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                navigationIcon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                title = { Text(text = "LOGIN") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 32.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp, Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                modifier = Modifier.testTag("username"),
                value = username,
                onValueChange = {
                    username = it
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Email, contentDescription = null)
                },
                label = {
                    Text(
                        text = "Email",
                        modifier = Modifier.testFieldLabel("password")
                    )
                },
            )
            OutlinedTextField(
                modifier = Modifier.testTag("password"),
                value = password,
                onValueChange = {
                    password = it
                },
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(Icons.Outlined.Lock, contentDescription = null)
                },
                label = {
                    Text(
                        text = "Password",
                        modifier = Modifier.testFieldLabel("password"),
                    )
                },
            )

            TextButton(
                onClick = onLogin,
                modifier = Modifier.testTag("login").fillMaxWidth(),
                enabled = isValid,
            ) {
                Text(text = "Login")
            }
        }
    }
}

val FieldTestKey = SemanticsPropertyKey<String>("FieldLabel")
var SemanticsPropertyReceiver.testFieldLabel by FieldTestKey

fun Modifier.testFieldLabel(label: String): Modifier {
    return semantics { testFieldLabel = label }
}

@Composable
@Preview(heightDp = 480)
fun PreviewLoginScreen() {
    MaterialTheme {
        LoginScreen {}
    }
}