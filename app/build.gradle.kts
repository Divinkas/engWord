plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Configuration.compileSdkVersion)
    buildToolsVersion(Configuration.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Configuration.minSdkVersion)
        targetSdkVersion(Configuration.targetSdkVersion)

        applicationId = "com.divinkas.app.words"
        versionCode = 1
        versionName = "1.0.0RC-1"
        multiDexEnabled = true

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    dataBinding.isEnabled = true

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(Dependencies.kotlinStandardLibrary)

    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.coreExtensions)
    implementation(Dependencies.AndroidX.multiDex)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.navigationFragment)
    implementation(Dependencies.AndroidX.navigationUi)
    implementation(Dependencies.AndroidX.lifecycleExtensions)
    implementation(Dependencies.AndroidX.viewModel)
    implementation(Dependencies.AndroidX.encryptedSharedPreferences)

    implementation(Dependencies.Misc.dexter)
    implementation(Dependencies.Misc.timber)

    implementation(Dependencies.View.pinView)
    implementation(Dependencies.View.maskedEditText)
    implementation(Dependencies.View.stepProgressBar)
    implementation(Dependencies.View.lottie)
    implementation(Dependencies.View.glide)
    implementation(Dependencies.View.multiDex)
    implementation(Dependencies.View.circleImageView)

    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.coreExt)
    implementation(Dependencies.Koin.scope)
    implementation(Dependencies.Koin.viewModel)
    implementation(Dependencies.Koin.ext)

    implementation(Dependencies.Room.roomRuntime)
    implementation(Dependencies.Room.roomKtx)
    kapt(Dependencies.Room.roomCompiler)

    implementation(Dependencies.Retrofit.okHttp)
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofitAdapterRx)
    implementation(Dependencies.Retrofit.gsonConverter)
    implementation(Dependencies.Retrofit.loggingInterceptor)

    testImplementation(Dependencies.jUnit)
    testImplementation(Dependencies.Koin.test)
}