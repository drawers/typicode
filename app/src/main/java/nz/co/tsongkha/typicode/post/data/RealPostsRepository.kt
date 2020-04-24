package nz.co.tsongkha.typicode.post.data

import nz.co.tsongkha.typicode.post.Post

class RealPostsRepository(private val postsDataSource: PostsDataSource) : PostsRepository {

    override suspend fun posts(): List<Post> {
        return postsDataSource.posts()
    }
}