import dependencies.BuildVersion
import dependencies.AndroidX
import dependencies.Google
import dependencies.Hilt
import dependencies.Kotlinx
import dependencies.Ktor
import dependencies.Serialization
import dependencies.Kotlin
import dependencies.Compose
import dependencies.Coil
import dependencies.Navigation



plugins {
    id("com.android.application")
    kotlin("android")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.5.31"
    id("kotlin-android")
    id ("com.google.devtools.ksp") version "1.6.10-1.0.2"

}

android {

    compileSdk = BuildVersion.compileSdk
//    buildToolsVersion = BuildVersion.buildTools
    defaultConfig {
        applicationId  = BuildVersion.appId
        minSdk = BuildVersion.minSdk
        targetSdk = BuildVersion.targetSdk
        versionCode  = BuildVersion.versionCode
        versionName = BuildVersion.versionName
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }



    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility  = JavaVersion.VERSION_1_8
        targetCompatibility  = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = BuildVersion.jvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }

    packagingOptions {
        exclude("/META-INF/{AL2.0,LGPL2.1}")
    }

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }


}



dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation (Kotlin.kotlinStdlib)

    implementation(AndroidX.constraintlayout)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.lifecycleVmKtx)
    implementation(AndroidX.livedataKtx)
    implementation(AndroidX.fragmentKtx)

    implementation(Google.material)
    implementation(Hilt.android)
    implementation(Hilt.hiltNavigation)

    kapt(Hilt.compiler)


    implementation(Coil.coil)


    implementation(Kotlinx.coroutinesCore)
    implementation(Compose.composeUi)
    implementation(Compose.composeMaterial)
    implementation(Compose.composeTools)
    implementation(Compose.composeActivity)


    implementation(Serialization.serialization)

    implementation(Ktor.core)
    implementation(Ktor.clientSerialization)
    implementation(Ktor.android)
    implementation(Ktor.ktorJson)
    implementation(Ktor.logging)


    implementation (Navigation.navigationCompose)
    implementation (Navigation.composeDestinations)
    ksp (Navigation.destinationsKsp)


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")



}
kapt {
    correctErrorTypes = true
}




//plugins {
//    id 'com.android.application'
//    id 'org.jetbrains.kotlin.android'
//}
//
//android {
//    compileSdk 32
//
//    defaultConfig {
//        applicationId "com.example.userservice"
//        minSdk 21
//        targetSdk 32
//        versionCode 1
//        versionName "1.0"
//
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//        vectorDrawables {
//            useSupportLibrary true
//        }
//    }
//
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//    compileOptions {
//        sourceCompatibility JavaVersion.VERSION_1_8
//        targetCompatibility JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = '1.8'
//    }
//    buildFeatures {
//        compose true
//    }
//    composeOptions {
//        kotlinCompilerExtensionVersion compose_version
//    }
//    packagingOptions {
//        resources {
//            excludes += '/META-INF/{AL2.0,LGPL2.1}'
//        }
//    }
//}
//
//dependencies {
//
//    implementation 'androidx.core:core-ktx:1.7.0'
//    implementation "androidx.compose.ui:ui:$compose_version"
//    implementation "androidx.compose.material:material:$compose_version"
//    implementation "androidx.compose.ui:ui-tooling-preview:$compose_version"
//    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.3.1'
//    implementation 'androidx.activity:activity-compose:1.3.1'
//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//    androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
//    debugImplementation "androidx.compose.ui:ui-tooling:$compose_version"
//    debugImplementation "androidx.compose.ui:ui-test-manifest:$compose_version"
//}