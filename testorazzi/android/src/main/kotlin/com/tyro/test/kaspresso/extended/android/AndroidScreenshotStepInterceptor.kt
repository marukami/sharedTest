package com.tyro.test.kaspresso.extended.android

import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.tyro.test.kaspresso.extended.api.PlatformScreenshotStepInterceptor

// TODO use upstream library
class AndroidScreenshotStepInterceptor(
  private val screenshots: Screenshots,
) : PlatformScreenshotStepInterceptor() {
  override fun intercept(tag: String) {
    screenshots.take(tag)
  }
}
