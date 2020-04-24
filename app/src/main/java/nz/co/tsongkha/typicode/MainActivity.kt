package nz.co.tsongkha.typicode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nz.co.tsongkha.R
import nz.co.tsongkha.typicode.ui.main.MainFragment
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
