@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

val sharedTestPath = "src/sharedTest/kotlin"
val sharedTest = layout.projectDirectory.dir(sharedTestPath)
val sharedAndroidTest = layout.buildDirectory.dir("sharedTest/androidTest/kotlin")

android {
    namespace = "dev.tilbrook.mylibrary"
    compileSdk = 34

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

val sharedAndroidTestTask = tasks.register<Copy>("copySharedAndroidTest") {
    from(layout.projectDirectory.files(sharedTestPath))
    into(sharedAndroidTest)
}

tasks.preBuild.dependsOn(sharedAndroidTestTask)
tasks.maybeCreate("prepareKotlinIdeaImport").dependsOn(sharedAndroidTestTask)

dependencies {

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.junit.ktx)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(projects.featureUiTest)
    testImplementation(projects.featureUiTest)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.robolectric)
    implementation(projects.testCore)
}