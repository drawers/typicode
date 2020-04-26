plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")
    defaultConfig {
        applicationId = "nz.co.tsongkha.typicode"
        minSdkVersion(24)
        targetSdkVersion(29)
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
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.72")

    implementation(project(":network"))

    implementation("com.yelp.android:bento:15.8.0")
    androidTestImplementation("com.yelp.android:bento-testing:15.8.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5")

    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.google.android.material:material:1.1.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    implementation("com.github.stephanenicolas.toothpick:ktp:3.1.0")
    implementation("com.github.stephanenicolas.toothpick:smoothie-lifecycle-viewmodel-ktp:3.1.0")
    implementation("com.github.stephanenicolas.toothpick:smoothie-lifecycle-ktp:3.1.0")
    kapt("com.github.stephanenicolas.toothpick:toothpick-compiler:3.1.0")


    testImplementation(project(":network"))
    testImplementation("junit:junit:4.13")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.0.4")
    testImplementation("io.kotest:kotest-assertions-core-jvm:4.0.4")
    testImplementation("io.kotest:kotest-property-jvm:4.0.4")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.5")
    testImplementation("io.mockk:mockk:1.10.0")

    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
