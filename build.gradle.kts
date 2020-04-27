import nz.co.typicode.buildsrc.SharedConfig.Kotlin.kotlinVersion

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
//    val kotlinVersion by rootProject.extra { "1.3.72" }

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.5.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${nz.co.typicode.buildsrc.SharedConfig.Kotlin.kotlinVersion}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()

    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}