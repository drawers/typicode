package nz.co.tsongkha.typicode.post.presentation.bento

data class CommentItem(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)