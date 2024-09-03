plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.client"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.client"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildFeatures {
        aidl = true
        dataBinding = true
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    //Room database
    val roomVersion = "2.4.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion") // To use Kotlin annotation processing tool (kapt)
    implementation("androidx.room:room-ktx:$roomVersion") // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-guava:$roomVersion") // optional - Guava support for Room, including Optional and ListenableFuture
    implementation("androidx.room:room-paging:$roomVersion") // optional - Paging 3 Integration

    //Rounded Image View
    implementation ("com.makeramen:roundedimageview:2.3.0")

    //Kotlin Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.3.0")

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // glide
    implementation(libs.glide)
}

kapt {
    correctErrorTypes = true
}