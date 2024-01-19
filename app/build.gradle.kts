plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")
    id("kotlin-kapt")
    id("kotlin-android")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.aceledacomposeui"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.aceledacomposeui"
        minSdk = 26
        targetSdk = 34
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
   /* kotlin {
        jvmToolchain(17)
    }
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }*/
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.compose.ui:ui-util:1.5.4")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0-rc02")

    // Image
    implementation ("io.coil-kt:coil-compose:2.5.0")

    // Data Store
    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Pager
    implementation("com.google.accompanist:accompanist-pager:0.28.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.28.0")

    implementation("com.google.accompanist:accompanist-flowlayout:0.20.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // Dagger
    val hiltVersion = "2.50"
    val hiltAndroidXVersion = "1.1.0"

    kapt("androidx.hilt:hilt-compiler:$hiltAndroidXVersion")
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltAndroidXVersion")

    // Bar code scanner
   /* val camerax_version = "1.3.1"
    implementation("androidx.camera:camera-camera2:${camerax_version}")
    implementation("androidx.camera:camera-lifecycle:${camerax_version}")
    implementation("androidx.camera:camera-view:${camerax_version}")
    implementation("com.google.mlkit:barcode-scanning:17.2.0")*/

    implementation("androidx.camera:camera-view:1.4.0-alpha03")

    implementation("com.google.android.gms:play-services-code-scanner:16.1.0")

    // Live data
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    // Drag and drop
    implementation("org.burnoutcrew.composereorderable:reorderable:0.7.4")

    implementation("com.google.code.gson:gson:2.10.1")

    //data store
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // CameraX
    implementation("androidx.camera:camera-camera2:1.3.0-alpha06")
    implementation("androidx.camera:camera-lifecycle:1.3.0-alpha06")
    implementation("androidx.camera:camera-view:1.3.0-alpha06")

    // MLKit
    implementation("com.google.mlkit:face-detection:16.1.5")
    implementation("com.google.mlkit:image-labeling:17.0.7")
    implementation("com.google.mlkit:object-detection:17.0.0")
    implementation("com.google.mlkit:barcode-scanning:17.1.0")
    implementation("com.google.mlkit:text-recognition:16.0.0-beta6")
    implementation("com.google.mlkit:face-mesh-detection:16.0.0-beta1")

    implementation("androidx.compose.ui:ui:1.5.0-beta01")
    implementation("androidx.navigation:navigation-compose:2.7.0-beta01")
}