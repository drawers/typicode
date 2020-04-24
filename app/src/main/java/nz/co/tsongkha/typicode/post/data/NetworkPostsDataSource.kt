package nz.co.tsongkha.typicode.post.data

import nz.co.tsongkha.typicode.network.service.post.CommentDto
import nz.co.tsongkha.typicode.network.service.post.PostDto
import nz.co.tsongkha.typicode.network.service.post.PostsService
import nz.co.tsongkha.typicode.post.domain.Comment
import nz.co.tsongkha.typicode.post.domain.Post
import toothpick.InjectConstructor

@InjectConstructor
class NetworkPostsDataSource(private val postsService: PostsService) : PostsDataSource {

    override suspend fun posts(): List<Post> {
        return postsService.posts()
            .map { it.toDomain() }
    }

    override suspend fun comments(postId: Int): List<Comment> {
        return postsService.comments(postId)
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

    private fun CommentDto.toDomain(): Comment {
        return Comment(
            postId = postId,
            id = id,
            name = name,
            email = email,
            body = body
        )
    }
}