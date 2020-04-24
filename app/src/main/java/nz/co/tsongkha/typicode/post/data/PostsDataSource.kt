package nz.co.tsongkha.typicode.post.data

import nz.co.tsongkha.typicode.post.Post

interface PostsDataSource {

    suspend fun posts(): List<Post>
}