package nz.co.tsongkha.typicode.network

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.defaultSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import javax.inject.Provider

internal class HttpClientProvider : Provider<HttpClient> {

    override fun get(): HttpClient {
        return HttpClient {

            install(JsonFeature) {
                defaultSerializer()
            }

            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }
}