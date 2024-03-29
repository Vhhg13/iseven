plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.iseven"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.iseven"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
    buildFeatures{
        viewBinding = true
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // For Compose:
    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.compiler:compiler:1.5.3")
    //implementation("androidx.compose.ui:ui")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.material:material:1.5.4")
    implementation("io.coil-kt:coil-compose:2.5.0")


    // For Hilt:
    implementation("com.google.dagger:hilt-android:2.48.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    implementation("androidx.work:work-runtime-ktx:2.8.1")
    implementation("androidx.hilt:hilt-work:1.0.0")


    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    //For Room:
    val room_version = "2.6.0"

    implementation("androidx.room:room-runtime:$room_version")


    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")


    // For Retrofit:
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.fragment:fragment-ktx:1.6.1")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
kapt {
    correctErrorTypes = true
}
