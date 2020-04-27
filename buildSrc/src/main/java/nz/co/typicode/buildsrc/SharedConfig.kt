package nz.co.typicode.buildsrc

object SharedConfig {

    object Kotlin {
        const val kotlinVersion = "1.3.72"
    }

    object Dependencies {
        const val toothpickVersion = "3.1.0"
        const val coroutinesVersion = "1.3.5"
        const val bentoVersion = "15.8.0"
        const val lifecycleVersion = "2.2.0"
        const val kotestVersion = "4.0.4"
        const val ktorVersion = "1.3.2"
    }

    object Android {
        const val compileSdkVersion = 29
        const val buildToolsVersion = "29.0.3"
        const val minSdkVersion = 24
        const val targetSdkVersion = 29
    }
}