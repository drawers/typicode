package nz.co.tsongkha.typicode.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nz.co.tsongkha.typicode.post.Post
import nz.co.tsongkha.typicode.post.data.PostsRepository
import nz.co.tsongkha.typicode.ui.main.bento.PostItem
import toothpick.ktp.delegate.inject

class MainViewModel : ViewModel() {

    private val postsRepository by inject<PostsRepository>()

    private val _posts = MutableLiveData<List<PostItem>>(null)

    val posts: LiveData<List<PostItem>> = _posts

    fun load() {
        viewModelScope.launch {
            val posts = postsRepository.posts().map { it.toViewProps() }
            _posts.value = posts
        }
    }

    private fun Post.toViewProps(): PostItem {
        return PostItem(
            id = id,
            title = title,
            body = body
        )
    }
}
