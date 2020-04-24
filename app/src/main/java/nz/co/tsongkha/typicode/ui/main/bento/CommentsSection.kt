package nz.co.tsongkha.typicode.ui.main.bento

data class CommentsSection(
    val state: State,
    val comments: List<CommentItem> = emptyList()
) {
    enum class State {
        EXPANDED, CONTRACTED
    }
}