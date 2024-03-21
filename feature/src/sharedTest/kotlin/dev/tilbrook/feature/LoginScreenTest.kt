package dev.tilbrook.feature

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

abstract class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val loginScreen = LoginScreen(composeTestRule)

    @Before
    fun setup() {
        composeTestRule.setContent {
            LoginScreen {
            }
        }
    }

    @Test
    fun can_login_if_username_and_password_is_valid() {
        loginScreen {
            username.performTextReplacement("m@tilbrook.dev")
            password.performTextReplacement("wordpass420")
            login {
                assertIsDisplayed()
                assertIsEnabled()
                performClick()
            }
        }
    }

    @Test
    fun can_not_login_if_password_is_empty() {
        loginScreen {
            username.performTextReplacement("m@tilbrook.dev")
            password.performTextReplacement("")
            login {
                assertIsNotEnabled()
            }
        }
    }

    @Test
    fun can_not_login_if_username_valid_and_password_is_not_valid() {
        loginScreen {
            username.performTextReplacement("m@tilbrook.dev")
            password.performTextReplacement("2shrt")
            login {
                assertIsNotEnabled()
            }
        }
    }
}