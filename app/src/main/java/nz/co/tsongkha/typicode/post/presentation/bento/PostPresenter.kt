package nz.co.tsongkha.typicode.post.presentation.bento

import com.yelp.android.bento.components.ListComponent
import com.yelp.android.bento.core.Component

class PostPresenter(
    private val loadComments: (Int) -> Unit,
    private val hideComments: (Int) -> Unit
) {

    fun onClick(postItem: PostItem) {
        when (postItem.commentsSection) {
            is CommentsSection.Loaded -> {
                hideComments(postItem.id)
            }
            CommentsSection.Loading -> {
                return
            }
            is CommentsSection.Empty -> {
                loadComments(postItem.id)
            }
        }
    }

    fun components(postItem: PostItem): List<Component> {
        when (postItem.commentsSection) {
            is CommentsSection.Empty -> {
                return emptyList()
            }
            is CommentsSection.Loading -> {
                return listOf(LoadingComponent())
            }
            is CommentsSection.Loaded -> {
                return listOf(
                    ListComponent<Nothing?, CommentItem>(
                        null,
                        CommentViewHolder::class.java
                    ).apply {
                        setData(postItem.commentsSection.comments)
                    }
                )
            }
        }
    }
}