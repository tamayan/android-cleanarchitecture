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

    val kotlinVersion by extra("1.4.0")
    val navVersion by extra("2.3.0")
    val lifecycleVersion by extra("2.2.0")
    val roomVersion by extra("2.2.5")
    val hiltVersion by extra("1.0.0-alpha02")
    val daggerHiltVersion by extra("2.28-alpha")
    val retrofitVersion by extra("2.9.0")
    val moshiVersion by extra("1.9.3")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    // AndroidX
    implementation("androidx.core:core-ktx:1.3.1")

    // AndroidX - Layout
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.recyclerview:recyclerview:1.1.0")

    // AndroidX - Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    // AndroidX - Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    // AndroidX - Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // AndroidX - Hilt
    implementation("androidx.hilt:hilt-common:$hiltVersion")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:$hiltVersion")
    kapt("androidx.hilt:hilt-compiler:$hiltVersion")

    // Dagger - Hilt Support
    implementation("com.google.dagger:hilt-android:$daggerHiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$daggerHiltVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:retrofit-mock:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    // Moshi
    implementation("com.squareup.moshi:moshi:$moshiVersion")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    implementation("com.squareup.moshi:moshi-adapters:$moshiVersion")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")

    // Material
    implementation("com.google.android.material:material:1.2.1")

    // Coil
    implementation("io.coil-kt:coil:1.0.0-rc1")

    // Timber
    implementation("com.jakewharton.timber:timber:4.7.1")

    // LeakCanary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.4")

    // Hyperion
    debugImplementation("com.willowtreeapps.hyperion:hyperion-core:0.9.29")

    // Test Library
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("junit:junit:4.13")
    testImplementation("com.nhaarman:mockito-kotlin:1.6.0")
    testImplementation("androidx.room:room-testing:$roomVersion")

    // AndroidX - Test Library
    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}

kapt {
    generateStubs = true
}

repositories {
    jcenter()
    maven(url = "https://maven.google.com")
}