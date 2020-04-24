package nz.co.tsongkha.typicode

import android.app.Application
import toothpick.ktp.KTP

class TypicodeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KTP.openScope(ApplicationScope::class.java)
            .installModules()
    }
}