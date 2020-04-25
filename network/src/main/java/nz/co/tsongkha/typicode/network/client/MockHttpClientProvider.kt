package nz.co.tsongkha.typicode.network.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import nz.co.tsongkha.typicode.network.service.BASE_URL
import toothpick.InjectConstructor
import java.io.File
import javax.inject.Provider

@InjectConstructor
class MockHttpClientProvider : Provider<HttpClient> {

    override fun get(): HttpClient {
        return HttpClient(MockEngine) {
            engine {
                addHandler { request ->
                    when {
                        request.url.fullPath == "$BASE_URL/posts" -> {
                            val body = File("nz/co/tsongkha/typicode/network/client/posts.json").readBytes()
                            respond(
                                content = body,
                                status = HttpStatusCode.OK,
                                headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
                            )
                        }
                        request.url.encodedPath.matches(
                            Regex(
                                "/posts/.+/comments"
                            )
                        ) -> {
                            val body = File("nz/co/tsongkha/typicode/network/client/comments.json").readBytes()
                            respond(
                                content = body,
                                status = HttpStatusCode.OK,
                                headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
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