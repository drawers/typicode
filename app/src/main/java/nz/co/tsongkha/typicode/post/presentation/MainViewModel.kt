package nz.co.tsongkha.typicode.post.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nz.co.tsongkha.typicode.post.data.PostsRepository
import nz.co.tsongkha.typicode.post.domain.Comment
import nz.co.tsongkha.typicode.post.domain.Post
import nz.co.tsongkha.typicode.post.presentation.bento.CommentItem
import nz.co.tsongkha.typicode.post.presentation.bento.CommentsSection
import nz.co.tsongkha.typicode.post.presentation.bento.PostItem
import toothpick.InjectConstructor
import toothpick.ktp.delegate.inject

class MainViewModel : ViewModel() {

    private val postsRepository by inject<PostsRepository>()

    private val _posts = MutableLiveData<List<PostItem>>(null)

    val posts: LiveData<List<PostItem>> = _posts

    fun load() {
        viewModelScope.launch {
            val posts = postsRepository.posts().map { it.toItem() }
            _posts.value = posts
        }
    }

    fun loadComments(id: Int) {
        viewModelScope.launch {
            val comments = postsRepository.comments(id)
            val commentsSection = comments.toCommentsSection()
            val posts = _posts.value
            val updatedPosts = posts?.map {
                when (it.id) {
                    id -> {
                        it.copy(
                            commentsSection = commentsSection
                        )
                    }
                    else -> {
                        it
                    }
                }
            }
            _posts.value = updatedPosts
        }
    }

    private fun Post.toItem(): PostItem {
        return PostItem(
            id = id,
            title = title,
            body = body,
            commentsSection = CommentsSection.EMPTY
        )
    }

    private fun List<Comment>.toCommentsSection(): CommentsSection {
        return CommentsSection(
            state = CommentsSection.State.EXPANDED,
            comments = this.map { it.toItem() }
        )
    }

    private fun Comment.toItem(): CommentItem {
        return CommentItem(
            postId = postId,
            id = id,
            name = name,
            email = email,
            body = body
        )
    }
}
