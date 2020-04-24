package nz.co.tsongkha.typicode.network.service.post

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import nz.co.tsongkha.typicode.network.service.BASE_URL
import toothpick.InjectConstructor

@InjectConstructor
class PostsService(private val httpClient: HttpClient) {

    suspend fun posts(): List<PostDto> {
        return httpClient.get("$BASE_URL/posts")
    }

    suspend fun comments(postId: Int): List<CommentDto> {
        return httpClient.get("$BASE_URL/posts/$postId/comments")
    }
}