package nz.co.tsongkha.typicode.network

import io.ktor.client.HttpClient
import nz.co.tsongkha.typicode.network.client.HttpClientProvider
import nz.co.tsongkha.typicode.network.client.MockHttpClientProvider
import nz.co.tsongkha.typicode.network.service.post.PostsService
import nz.co.tsongkha.typicode.network.service.post.HttpPostsService
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module

val networkModule = module {
    bind<HttpClient>().toProvider(HttpClientProvider::class)
    bind<PostsService>().toClass(HttpPostsService::class)
}

val testNetworkModule = module {
    bind<HttpClient>().toProvider(MockHttpClientProvider::class)
    bind<PostsService>().toClass(HttpPostsService::class)
}