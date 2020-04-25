package nz.co.tsongkha.typicode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nz.co.tsongkha.R
import nz.co.tsongkha.typicode.post.presentation.MainFragment

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
