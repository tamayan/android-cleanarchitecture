// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    val kotlinVersion by extra("1.4.0")

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        // AndroidX - Navigation
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0")
        // Dagger Android Hilt
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.28-alpha")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

tasks.create("clean", type = Delete::class) {
    delete(rootProject.buildDir)
}
