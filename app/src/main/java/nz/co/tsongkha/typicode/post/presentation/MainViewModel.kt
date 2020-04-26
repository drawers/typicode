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
            update(id) {
                copy(commentsSection = CommentsSection.Loading)
            }

            val comments = postsRepository.comments(id)
            val commentsSection = comments.toCommentsSection()

            update(id) {
                copy(commentsSection = commentsSection)
            }
        }
    }

    fun hideComments(id: Int) {
        update(id) {
            copy(commentsSection = CommentsSection.Empty)
        }
    }

    private fun Post.toItem(): PostItem {
        return PostItem(
            id = id,
            title = title,
            body = body,
            commentsSection = CommentsSection.Empty
        )
    }

    private fun List<Comment>.toCommentsSection(): CommentsSection {
        return CommentsSection.Loaded(
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

    /**
     * Update matching member of the list and post the update to LiveData
     */
    private fun update(id: Int, update: PostItem.() -> PostItem) {
        val current = _posts.value ?: return
        val updated = current.map {
            when (it.id) {
                id -> {
                    it.update()
                }
                else -> {
                    it
                }
            }
        }
        _posts.value = updated
    }
}
