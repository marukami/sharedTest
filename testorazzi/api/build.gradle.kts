plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.tyro.test.kaspresso.extended.api"
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

dependencies {
    api(libs.junit)
    val excludeCheckerframework: ExternalModuleDependency.() -> Unit = {
        exclude(group = "org.checkerframework", module = "checker")
        exclude(group = "com.google.android.material", module = "material")
    }
    api(libs.test.kaspresso.core, excludeCheckerframework)
    api(libs.test.kaspresso.compose, excludeCheckerframework)
//    api(catalog.bundles.coroutines.test)
}
