package nz.co.tsongkha.typicode

import android.app.Application
import nz.co.tsongkha.typicode.network.networkModule
import nz.co.tsongkha.typicode.post.data.postsDataModule
import toothpick.ktp.KTP

class TypicodeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KTP.openScope(ApplicationScope::class.java)
            .installModules(
                networkModule,
                postsDataModule
            )
    }
}