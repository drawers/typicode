package nz.co.tsongkha.typicode.post.data

import nz.co.tsongkha.typicode.post.Post
import toothpick.InjectConstructor

@InjectConstructor
class RealPostsRepository(private val postsDataSource: PostsDataSource) : PostsRepository {

    override suspend fun posts(): List<Post> {
        return postsDataSource.posts()
    }
}