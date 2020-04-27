import nz.co.typicode.buildsrc.SharedConfig
import nz.co.typicode.buildsrc.SharedConfig.Android.buildToolsVersion
import nz.co.typicode.buildsrc.SharedConfig.Android.compileSdkVersion
import nz.co.typicode.buildsrc.SharedConfig.Android.minSdkVersion
import nz.co.typicode.buildsrc.SharedConfig.Android.targetSdkVersion
import nz.co.typicode.buildsrc.SharedConfig.Dependencies.bentoVersion
import nz.co.typicode.buildsrc.SharedConfig.Dependencies.coroutinesVersion
import nz.co.typicode.buildsrc.SharedConfig.Dependencies.kotestVersion
import nz.co.typicode.buildsrc.SharedConfig.Dependencies.lifecycleVersion
import nz.co.typicode.buildsrc.SharedConfig.Dependencies.toothpickVersion
import nz.co.typicode.buildsrc.SharedConfig.Kotlin.kotlinVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(SharedConfig.Android.compileSdkVersion)
    buildToolsVersion(SharedConfig.Android.buildToolsVersion)
    defaultConfig {
        applicationId = "nz.co.tsongkha.typicode"
        minSdkVersion(SharedConfig.Android.minSdkVersion)
        targetSdkVersion(SharedConfig.Android.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")

    implementation(project(":network"))

    implementation("com.yelp.android:bento:$bentoVersion")
    androidTestImplementation("com.yelp.android:bento-testing:$bentoVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.core:core-ktx:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.google.android.material:material:1.1.0")
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    implementation("com.github.stephanenicolas.toothpick:ktp:$toothpickVersion")
    implementation("com.github.stephanenicolas.toothpick:smoothie-lifecycle-viewmodel-ktp:$toothpickVersion")
    implementation("com.github.stephanenicolas.toothpick:smoothie-lifecycle-ktp:$toothpickVersion")
    kapt("com.github.stephanenicolas.toothpick:toothpick-compiler:$toothpickVersion")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-property-jvm:$kotestVersion")

    testImplementation("io.mockk:mockk:1.10.0")

    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
}