import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

val sharedTest = layout.projectDirectory.dir("src/sharedTest")
val sharedAndroidTest = layout.buildDirectory.dir("sharedTest/androidTest/kotlin")

android {
    namespace = "dev.tilbrook.feature.ui.test"
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
            java.srcDir(sharedAndroidTest)
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
}

dependencies {
    api(libs.junit)
    api(libs.androidx.junit)
    api(libs.androidx.junit.ktx)
    api(libs.androidx.espresso.core)
    api(libs.androidx.ui.test.junit4)

    val excludeCheckerframework: ExternalModuleDependency.() -> Unit = {
        exclude(group = "org.checkerframework", module = "checker")
    }
    api(libs.test.kaspresso.core, excludeCheckerframework)
    api(libs.test.kaspresso.compose, excludeCheckerframework)
    api(libs.test.kakao.core, excludeCheckerframework)
    api(libs.test.kakao.compose)
    implementation(projects.testCore)
}