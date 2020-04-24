package nz.co.tsongkha.typicode.post.domain

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)