package nz.co.tsongkha.typicode.post.presentation.bento

sealed class CommentsSection {

    object Empty : CommentsSection()

    object Loading : CommentsSection()

    data class Loaded(val comments: List<CommentItem>) : CommentsSection()
}