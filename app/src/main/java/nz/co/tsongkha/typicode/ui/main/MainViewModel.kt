package nz.co.tsongkha.typicode.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nz.co.tsongkha.typicode.post.Post
import nz.co.tsongkha.typicode.post.data.PostsRepository
import nz.co.tsongkha.typicode.ui.main.bento.PostViewProps
import toothpick.ktp.delegate.inject

class MainViewModel : ViewModel() {

    private val postsRepository by inject<PostsRepository>()

    init {

    }

    lateinit var posts: LiveData<List<PostViewProps>>

    private lateinit var _posts: MutableLiveData<List<Post>>

    suspend fun load() {
        viewModelScope.launch {
            val posts = postsRepository.posts()
            _posts.value = posts
        }
    }
}
