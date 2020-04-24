package nz.co.tsongkha.typicode.network.client

import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.defaultSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
internal class HttpClientProvider : Provider<HttpClient> {

    override fun get(): HttpClient {
        return HttpClient {

            install(JsonFeature) {
                serializer = GsonSerializer()
            }

            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }
}