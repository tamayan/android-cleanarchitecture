object Packages {
    const val applicationId = "com.example.cleanarchitecture"
    const val debugNameSuffix = ".debug"
}

object Versions {
    const val kotlin = "1.4.0"

    const val compileSdk = 30
    const val targetSdk = compileSdk
    const val minSdk = 24

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0
    private const val versionOffset = 0

    val versionCode = ((1 + versionMajor) * 10000 + versionMinor * 100 + versionPatch) * 100 + versionOffset
    val versionName = "$versionMajor.$versionMinor.$versionPatch"
}

object Libs {

    object GradlePlugin {
        const val android = "com.android.tools.build:gradle:4.0.1"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val navigation = "androidx.navigation:navigation-safe-args-gradle-plugin:${AndroidX.Navigation.version}"
        const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${Dagger.Hilt.version}"
    }

    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.3.1"
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.1"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
        const val testing = "androidx.arch.core:core-testing:2.1.0"

        object Navigation {
            const val version = "2.3.0"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
        }

        object Lifecycle {
            private const val version = "2.2.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
        }

        object Room {
            private const val version = "2.2.5"
            const val runtime = "androidx.room:room-runtime:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val test = "androidx.room:room-testing:$version"
        }

        object Hilt {
            private const val version = "1.0.0-alpha02"
            const val common = "androidx.hilt:hilt-common:$version"
            const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$version"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
        }

        object Test {
            const val runner = "androidx.test:runner:1.3.0"
            const val junit = "androidx.test.ext:junit:1.1.2"
            const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
        }
    }

    object Dagger {
        object Hilt {
            const val version = "2.28-alpha"
            const val android = "com.google.dagger:hilt-android:$version"
            const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        }
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val core = "com.squareup.retrofit2:retrofit:$version"
        const val mock = "com.squareup.retrofit2:retrofit-mock:$version"
        const val convertor = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object Moshi {
        private const val version = "1.9.3"
        const val core = "com.squareup.moshi:moshi:$version"
        const val kotlin = "com.squareup.moshi:moshi-kotlin:$version"
        const val adapters = "com.squareup.moshi:moshi-adapters:$version"
        const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    object Material {
        const val material = "com.google.android.material:material:1.2.1"
    }

    object Coil {
        const val coil = "io.coil-kt:coil:1.0.0-rc1"
    }

    object Timber {
        const val timber = "com.jakewharton.timber:timber:4.7.1"
    }

    object LeakCanary {
        const val android = "com.squareup.leakcanary:leakcanary-android:2.4"
    }

    object Hyperion {
        const val core = "com.willowtreeapps.hyperion:hyperion-core:0.9.29"
    }

    object JUnit {
        const val junit = "junit:junit:4.13"
    }

    object Mockito {
        const val kotlin = "com.nhaarman:mockito-kotlin:1.6.0"
    }
}
