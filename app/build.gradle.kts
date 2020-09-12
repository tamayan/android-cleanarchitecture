import org.jetbrains.kotlin.konan.properties.Properties

val local = Properties()
val localProperties: File = rootProject.file("local.properties")
if (localProperties.exists()) {
    localProperties.inputStream().use { local.load(it) }
}

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")

    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {

    compileSdkVersion(Versions.compileSdk)

    defaultConfig {
        targetSdkVersion(Versions.targetSdk)
        minSdkVersion(Versions.minSdk)

        applicationId = Packages.applicationId
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "ROOM_DATABASE_NAME", "\"AppDatabase.db\"")
        buildConfigField("String", "THUMBNAIL_URL", "\"https://img.youtube.com/vi/%s/maxresdefault.jpg\"")
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            buildConfigField("String", "BASE_URL", "\"${local.getProperty("release_base_url")}\"")
            buildConfigField("String", "BASIC_USER_NAME", "\"${local.getProperty("release_user_name")}\"")
            buildConfigField("String", "BASIC_PASS", "\"${local.getProperty("release_password")}\"")
        }

        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            applicationIdSuffix = Packages.debugNameSuffix

            buildConfigField("String", "BASE_URL", "\"${local.getProperty("debug_base_url")}\"")
            buildConfigField("String", "BASIC_USER_NAME", "\"${local.getProperty("debug_user_name")}\"")
            buildConfigField("String", "BASIC_PASS", "\"${local.getProperty("debug_password")}\"")
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation(Libs.DeployGate.sdk)

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