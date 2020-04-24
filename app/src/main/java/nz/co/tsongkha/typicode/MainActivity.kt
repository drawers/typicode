package nz.co.tsongkha.typicode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nz.co.tsongkha.R
import nz.co.tsongkha.typicode.network.service.post.PostsService
import nz.co.tsongkha.typicode.ui.main.MainFragment
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

class MainActivity : AppCompatActivity() {

    private val postService by inject<PostsService>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        KTP.openScope(ApplicationScope::class.java).inject(this)

        GlobalScope.launch {
            postService.posts().also { posts -> posts.forEach { println(it) }}
        }
    }
}
