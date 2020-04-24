package nz.co.tsongkha.typicode.post.data

import nz.co.tsongkha.typicode.post.Comment
import nz.co.tsongkha.typicode.post.Post

interface PostsRepository {

    suspend fun posts(): List<Post>

    suspend fun comments(postId: Int): List<Comment>
}