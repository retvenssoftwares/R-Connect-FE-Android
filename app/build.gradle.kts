plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}



android {
    namespace = "rconnect.retvens.technologies"
    compileSdk = 33

    defaultConfig {
        applicationId = "rconnect.retvens.technologies"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures{
        viewBinding =  true
    }


}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Country Code Picker

    implementation("com.hbb20:ccp:2.5.3")
    implementation ("com.github.bumptech.glide:glide:4.16.0")


    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")

    implementation ("com.github.saran2020:DragRating:2.1.0")

    //Maps
    implementation ("com.google.maps.android:android-maps-utils:2.3.0")
    implementation ("com.google.android.gms:play-services-maps:18.1.0")
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.google.maps.android:maps-utils-ktx:3.4.0")
    implementation ("androidx.core:core-ktx:1.10.1")

    //ProgressBar
    implementation ("com.github.MackHartley:RoundedProgressBar:3.0.0")




}