package com.tyro.test.kaspresso.extended

import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.tyro.test.kaspresso.extended.api.PlatformScreenshotStepInterceptor

fun Kaspresso.Builder.screenshotStepInterceptor(): StepWatcherInterceptor =
  if (isAndroidRuntime) {
    val cl =
      Class.forName(
        "com.tyro.test.kaspresso.extended.android.AndroidScreenshotStepInterceptor",
      )
    val con = cl.getDeclaredConstructor(Screenshots::class.java)
    con.newInstance(screenshots)
  } else {
    val cl =
      Class.forName(
        "com.tyro.test.kaspresso.extended.robolectric.JvmScreenshotStepInterceptor",
      )
    cl.getDeclaredConstructor().newInstance()
  } as PlatformScreenshotStepInterceptor
