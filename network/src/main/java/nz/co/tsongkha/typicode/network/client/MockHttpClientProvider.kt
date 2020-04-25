package nz.co.tsongkha.typicode.network.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import toothpick.InjectConstructor
import java.io.File
import javax.inject.Provider

@InjectConstructor
class MockHttpClientProvider : Provider<HttpClient> {

    override fun get(): HttpClient {
        return HttpClient(MockEngine) {

            install(JsonFeature) {
                serializer = GsonSerializer()
            }

            engine {
                addHandler { request ->
                    when {
                        request.url.fullPath == "/posts" -> {
                            val body = File("../network/src/main/java/nz/co/tsongkha/typicode/network/client/posts.json").readBytes()
                            respond(
                                content = body,
                                status = HttpStatusCode.OK,
                                headers = headersOf(
                                    "Content-Type" to listOf(
                                        ContentType.Application.Json.toString()
                                    )
                                )
                            )
                        }
                        request.url.fullPath.matches(
                            Regex(
                                "/posts/.+/comments"
                            )
                        ) -> {
                            val body = File("..network/src/main/java/nz/co/tsongkha/typicode/network/client/comments.json").readBytes()
                            respond(
                                content = body,
                                status = HttpStatusCode.OK,
                                headers = headersOf(
                                    "Content-Type" to listOf(
                                        ContentType.Application.Json.toString()
                                    )
                                )
                            )
                        }
                        else -> {
                            throw IllegalArgumentException("Unknown route")
                        }
                    }
                }
            }

        }
    }
}