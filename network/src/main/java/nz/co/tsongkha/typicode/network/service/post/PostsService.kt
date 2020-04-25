package nz.co.tsongkha.typicode.network.service.post

interface PostsService {
    suspend fun posts(): List<PostDto>

    suspend fun comments(postId: Int): List<CommentDto>
}