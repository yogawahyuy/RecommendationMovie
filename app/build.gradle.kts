import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

val authKey : String = gradleLocalProperties(rootDir).getProperty("HEADER_AUTH")
val apiKey:String = gradleLocalProperties(rootDir).getProperty("API_KEY")
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.yogawahyuy.recommendationmovie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yogawahyuy.recommendationmovie"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    buildTypes {
        debug {
            buildConfigField("String","authKey",authKey)
            buildConfigField("String","apiKey",apiKey)
        }
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.activity:activity:1.8.0")
    implementation("com.facebook.shimmer:shimmer:0.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.retrofit2:adapter-rxjava:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.picasso:picasso:2.8")
    implementation("io.reactivex:rxandroid:1.2.1")
    implementation("io.reactivex:rxjava:1.3.8")
    implementation ("com.squareup.picasso:picasso:2.8")
    //hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt ("com.google.dagger:hilt-android-compiler:2.50")

    implementation("me.relex:circleindicator:2.1.6")
    implementation("org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:2.1.0")
}
kapt {
    correctErrorTypes = true
}