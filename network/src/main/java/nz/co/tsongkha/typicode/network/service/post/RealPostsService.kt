package nz.co.tsongkha.typicode.network.service.post

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import nz.co.tsongkha.typicode.network.service.BASE_URL
import toothpick.InjectConstructor

@InjectConstructor
internal class RealPostsService(private val httpClient: HttpClient) : PostsService {

    override suspend fun posts(): List<PostDto> {
        return httpClient.get("$BASE_URL/posts")
    }

    override suspend fun comments(postId: Int): List<CommentDto> {
        return httpClient.get("$BASE_URL/posts/$postId/comments")
    }
}