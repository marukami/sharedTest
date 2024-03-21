package com.tyro.test.kaspresso.extended.api

import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

abstract class PlatformScreenshotStepInterceptor : StepWatcherInterceptor {
    override fun interceptAfterWithSuccess(stepInfo: StepInfo) {
        intercept("${makeTag(stepInfo)}_end")
    }

    override fun interceptBefore(stepInfo: StepInfo) {
        intercept("${makeTag(stepInfo)}_!start")
    }

    override fun interceptAfterWithError(
        stepInfo: StepInfo,
        error: Throwable,
    ) {
        intercept("${makeTag(stepInfo)}_failure_${error.javaClass.simpleName}")
    }

    abstract fun intercept(tag: String)

    private fun makeTag(stepInfo: StepInfo): String {
        val clazz = stepInfo.testClassName
        return "${clazz}_step_${stepInfo.ordinal}"
    }
}
