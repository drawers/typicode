package nz.co.tsongkha.typicode.post.presentation.bento

data class CommentsSection(
    val state: State,
    val comments: List<CommentItem>
) {
    companion object {
        val EMPTY = CommentsSection(
            state = State.CONTRACTED,
            comments = emptyList()
        )
    }

    enum class State {
        EXPANDED, CONTRACTED
    }
}