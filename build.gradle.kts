// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Libs.GradlePlugin.android)
        classpath(Libs.GradlePlugin.kotlin)
        classpath(Libs.GradlePlugin.navigation)
        classpath(Libs.GradlePlugin.daggerHilt)
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
