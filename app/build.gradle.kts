import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    // Dagger Hilt
    id("dagger.hilt.android.plugin")
    // AndroidX Navigation safeargs
    id("androidx.navigation.safeargs.kotlin")
}

fun getLocalProperties(key: String): String {
    return gradleLocalProperties(rootDir).getProperty(key)
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.1"

    defaultConfig {
        applicationId = "com.example.cleanarchitecture"

        minSdkVersion(24)
        targetSdkVersion(30)

        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "ROOM_DATABASE_NAME", "\"AppDatabase.db\"")
        buildConfigField("String", "THUMBNAIL_URL", "\"http://img.youtube.com/vi/%s/maxresdefault.jpg\"")
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            buildConfigField("String", "BASE_URL", "\"${getLocalProperties("release_base_url")}\"")
            buildConfigField("String", "BASIC_USER_NAME", "\"${getLocalProperties("release_user_name")}\"")
            buildConfigField("String", "BASIC_PASS", "\"${getLocalProperties("release_password")}\"")
        }

        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"

            buildConfigField("String", "BASE_URL", "\"${getLocalProperties("debug_base_url")}\"")
            buildConfigField("String", "BASIC_USER_NAME", "\"${getLocalProperties("debug_user_name")}\"")
            buildConfigField("String", "BASIC_PASS", "\"${getLocalProperties("debug_password")}\"")
        }
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation(Libs.Kotlin.stdLib)

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.AndroidX.swipeRefreshLayout)
    implementation(Libs.AndroidX.recyclerView)

    implementation(Libs.AndroidX.Navigation.ui)
    implementation(Libs.AndroidX.Navigation.fragment)

    implementation(Libs.AndroidX.Lifecycle.extensions)
    implementation(Libs.AndroidX.Lifecycle.viewModel)
    implementation(Libs.AndroidX.Lifecycle.liveData)

    implementation(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.ktx)
    kapt(Libs.AndroidX.Room.compiler)

    implementation(Libs.AndroidX.Hilt.common)
    implementation(Libs.AndroidX.Hilt.viewModel)
    kapt(Libs.AndroidX.Hilt.compiler)

    implementation(Libs.Dagger.Hilt.android)
    kapt(Libs.Dagger.Hilt.compiler)

    implementation(Libs.Retrofit.core)
    implementation(Libs.Retrofit.mock)
    implementation(Libs.Retrofit.convertor)

    implementation(Libs.Moshi.core)
    implementation(Libs.Moshi.kotlin)
    implementation(Libs.Moshi.adapters)
    kapt(Libs.Moshi.codegen)

    implementation(Libs.Material.material)
    implementation(Libs.Coil.coil)
    implementation(Libs.Timber.timber)

    // debug
    debugImplementation(Libs.LeakCanary.android)
    debugImplementation(Libs.Hyperion.core)

    // test
    testImplementation(Libs.AndroidX.testing)
    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.Mockito.kotlin)
    testImplementation(Libs.AndroidX.Room.test)

    // android test
    androidTestImplementation(Libs.AndroidX.Test.runner)
    androidTestImplementation(Libs.AndroidX.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.espresso)
}

kapt {
    generateStubs = true
}

repositories {
    jcenter()
    maven(url = "https://maven.google.com")
}