package nz.co.tsongkha.typicode.post.data

import nz.co.tsongkha.typicode.network.service.post.PostDto
import nz.co.tsongkha.typicode.network.service.post.PostsService
import nz.co.tsongkha.typicode.post.Post
import toothpick.InjectConstructor

@InjectConstructor
class NetworkPostsDataSource(private val postsService: PostsService) : PostsDataSource {

    override suspend fun posts(): List<Post> {
        return postsService.posts()
            .map { it.toDomain() }
    }

    private fun PostDto.toDomain(): Post {
        return Post(
            userId = this.userId,
            id = this.id,
            title = this.title,
            body = this.body
        )
    }
}