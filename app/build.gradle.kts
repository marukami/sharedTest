@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.tasks.factory.dependsOn


plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.roborazzi)
}

val sharedTest = layout.projectDirectory.dir("src/sharedTest/kotlin")
val sharedAndroidTest = layout.buildDirectory.dir("sharedTest/androidTest/kotlin")

android {
  namespace = "dev.tilbrook.sharedtest"
  compileSdk = 34

  defaultConfig {
    applicationId = "dev.tilbrook.sharedtest"
    minSdk = 29
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  sourceSets {
    getByName("test") {
      kotlin.srcDir(sharedTest)
    }
    getByName("androidTest") {
      kotlin.srcDir(sharedAndroidTest)
    }
  }

  testOptions {
    unitTests.all {
      it.systemProperty("robolectric.graphicsMode", "NATIVE")
    }
    managedDevices {
      localDevices {
        create("pixel2api34") {
          device = "Pixel 2"
          apiLevel = 34
          systemImageSource = "aosp"
        }
        create("pixel2api29") {
          device = "Pixel 2"
          apiLevel = 34
          systemImageSource = "aosp"
        }
      }
      groups {
        create("minAndMaxPhoneApi") {
          targetDevices.add(devices["pixel2api34"])
          targetDevices.add(devices["pixel2api29"])
        }
      }
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.11"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

val sharedAndroidTestTask = tasks.register<Copy>("copySharedAndroidTest") {
  from(sharedTest)
  into(sharedAndroidTest)
}

tasks.preBuild.dependsOn(sharedAndroidTestTask)
tasks.maybeCreate("prepareKotlinIdeaImport").dependsOn(sharedAndroidTestTask)


dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  testImplementation(libs.junit)
  testImplementation(libs.robolectric)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  androidTestImplementation(libs.androidx.test.core)
  testImplementation(libs.androidx.test.core)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
  debugImplementation(libs.test.kaspresso.debug)

  implementation(projects.feature)
  testImplementation(projects.featureUiTest)
  androidTestImplementation(projects.featureUiTest)
  testImplementation(projects.testorazzi)
  androidTestImplementation(projects.testorazzi)
  testImplementation(projects.testorazzi.jvm)
  androidTestImplementation(projects.testorazzi.android)

}

configurations.configureEach {
  resolutionStrategy {
    force(libs.junit.get())
    // Temporary workaround for https://issuetracker.google.com/174733673
    // force("org.objenesis:objenesis:3.2")

    // com.google.android.apps.common.testing.accessibility.framework.accessibility-test-framework•3.1
    // Causes a java.lang.ClassNotFoundException: Didn't find class "org.hamcrest.Matchers"
    // cause the accessibility framework is using hamcrest 2.2 where espresso needs 1.3
    force(libs.test.hamcrest.core.get())
    force(libs.test.hamcrest.library.get())
    force(libs.test.hamcrest.integration.get())
  }

}