package nz.co.tsongkha.typicode.post.presentation.bento

data class PostItem(
    val id: Int,
    val title: String,
    val body: String,
    val commentsSection: CommentsSection
) {
    fun loading(): PostItem = copy(
        commentsSection = CommentsSection.Loading
    )
}