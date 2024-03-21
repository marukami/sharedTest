package com.tyro.test.kaspresso.extended.robolectric

import com.github.takahirom.roborazzi.DEFAULT_ROBORAZZI_OUTPUT_DIR_PATH
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.RoborazziOptions
import com.github.takahirom.roborazzi.RoborazziTaskType
import com.github.takahirom.roborazzi.captureScreenRoboImage
import com.github.takahirom.roborazzi.provideRoborazziContext
import com.tyro.test.kaspresso.extended.api.PlatformScreenshotStepInterceptor

// TODO use upstream library
@OptIn(ExperimentalRoborazziApi::class)
class JvmScreenshotStepInterceptor : PlatformScreenshotStepInterceptor() {
  override fun intercept(tag: String) {
    captureScreenRoboImage(
      "build/outputs/kaspresso/roborazzi/$tag.png",
      RoborazziOptions(taskType = RoborazziTaskType.Record)
    )
  }
}
