plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.jetbrains.kotlin.android)
}

android {
  namespace = "com.tyro.test.Kaspresso"
  compileSdk = 34

  defaultConfig {
    minSdk = 29

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
}

configurations {
  create("androidTestDependency") {
    extendsFrom(implementation.get())
  }
  create("testDependency") {
    extendsFrom(implementation.get())
  }
}

dependencies {
  projects {
    implementation(testorazzi.api)
    "androidTestDependency"(testorazzi.android)
    "testDependency"(testorazzi.jvm)
  }
}
