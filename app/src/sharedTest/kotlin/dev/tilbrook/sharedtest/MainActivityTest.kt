package dev.tilbrook.sharedtest

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.core.app.launchActivity
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.tyro.test.kaspresso.extended.screenshotStepInterceptor
import dev.tilbrook.feature.LoginScreen
import dev.tilbrook.feature.TransactionRowNode
import dev.tilbrook.feature.TransactionsScreen
import dev.tilbrook.feature.WelcomeScreen
import org.junit.Rule
import org.junit.Test

abstract class MainActivityTest :
  TestCase(
    kaspressoBuilder =
    Kaspresso.Builder.withComposeSupport {
      val testOutputPath = "./build/outputs/kaspresso"
      beforeEachTest {
        launchActivity<MainActivity>()
        if (!isAndroidRuntime) return@beforeEachTest
        adbServer.performCmd(
          "bash", listOf(
            "-c", "./make-test-outputs-dir.sh"
          )
        )
      }
      afterEachTest {
        if (!isAndroidRuntime) return@afterEachTest
        adbServer.performAdb(
          "pull",
          listOf(
            "/sdcard/Documents/screenshots/${MainActivityTest::class.qualifiedName}",
            testOutputPath
          )
        )
      }
      stepWatcherInterceptors.add(screenshotStepInterceptor())
    },
  ) {

  @get:Rule
  val composeRule = createEmptyComposeRule()

  private val welcomeScreen = WelcomeScreen(composeRule)
  private val loginScreen = LoginScreen(composeRule)
  private val transactionsScreen = TransactionsScreen(composeRule)

  @OptIn(ExperimentalTestApi::class)
  @Test
  fun can_login_and_find_transaction() {
    run {

      step("Select Login") {
        welcomeScreen {
          login.performClick()
        }
        loginScreen {
          assertIsDisplayed()
        }
      }

      step("Login with user") {
        loginScreen {
          username.performTextReplacement("username")
          password.performTextReplacement("superpassword@31415")
          login.performClick()
        }
        transactionsScreen {
          assertIsDisplayed()
        }
      }

      step("Find Transaction") {
        transactionsScreen {
          transactions {
            childAt<TransactionRowNode>(67) {
              amount.assertTextContains("¥ 167")
            }
          }
        }
      }
    }
  }
}