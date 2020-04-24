package nz.co.tsongkha.typicode.post.data

import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module

val postsDataModule = module {
    bind<PostsRepository>().toClass(RealPostsRepository::class)
    bind<PostsDataSource>().toClass(NetworkPostsDataSource::class)
}