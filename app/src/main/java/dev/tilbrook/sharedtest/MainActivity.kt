package dev.tilbrook.sharedtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.tilbrook.feature.LoginScreen
import dev.tilbrook.feature.Transaction
import dev.tilbrook.feature.TransactionsScreen
import dev.tilbrook.feature.WelcomeScreen
import dev.tilbrook.sharedtest.ui.theme.SharedTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            SharedTestTheme {
                var screen: Screen by remember {
                    mutableStateOf(Screen.Welcome)
                }
                when (screen) {
                    Screen.Welcome -> WelcomeScreen {
                        screen = Screen.Login
                    }

                    Screen.Login -> LoginScreen {
                        screen = Screen.Transactions
                    }

                    Screen.Transactions -> TransactionsScreen()
                }
            }
        }
    }
}

enum class Screen {
    Welcome,
    Login,
    Transactions,
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SharedTestTheme {
        Greeting("Android")
    }
}