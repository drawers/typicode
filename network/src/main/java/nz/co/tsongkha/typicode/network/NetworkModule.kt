package nz.co.tsongkha.typicode.network

import io.ktor.client.HttpClient
import nz.co.tsongkha.typicode.network.client.HttpClientProvider
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module

val networkModule = module {
    bind<HttpClient>().toProvider(HttpClientProvider::class)
}