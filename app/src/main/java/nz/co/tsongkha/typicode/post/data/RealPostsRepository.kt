package nz.co.tsongkha.typicode.post.data

import nz.co.tsongkha.typicode.post.domain.Comment
import nz.co.tsongkha.typicode.post.domain.Post
import toothpick.InjectConstructor

@InjectConstructor
class RealPostsRepository(private val postsDataSource: PostsDataSource) : PostsRepository {

    override suspend fun posts(): List<Post> {
        return postsDataSource.posts()
    }

    override suspend fun comments(postId: Int): List<Comment> {
        return postsDataSource.comments(postId)
    }
}
