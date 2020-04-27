package nz.co.tsongkha.typicode.post.presentation

import androidx.lifecycle.Observer
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import nz.co.tsongkha.typicode.network.testNetworkModule
import nz.co.tsongkha.typicode.post.data.postsDataModule
import nz.co.tsongkha.typicode.post.presentation.bento.CommentItem
import nz.co.tsongkha.typicode.post.presentation.bento.CommentsSection
import nz.co.tsongkha.typicode.post.presentation.bento.PostItem
import toothpick.ktp.KTP

@ExperimentalCoroutinesApi
class MainViewModelTest : StringSpec() {

    private lateinit var viewModel: MainViewModel

    private lateinit var itemsObserver: Observer<List<PostItem>>

    private lateinit var capturedItems: MutableList<List<PostItem>>

    private val testListener = object : TestListener {

        override suspend fun beforeTest(testCase: TestCase) {
            super.beforeTest(testCase)

            viewModel = MainViewModel()
            KTP.openScope(this)
                .installModules(
                    testNetworkModule,
                    postsDataModule
                ).run {
                    inject(viewModel)
                }

            itemsObserver = mockk(relaxed = true)
            capturedItems = mutableListOf()
            every {
                itemsObserver.onChanged(capture(capturedItems))
            } answers { }

            viewModel.posts.observeForever(itemsObserver)
        }

        override suspend fun afterTest(testCase: TestCase, result: TestResult) {
            super.afterTest(testCase, result)
            viewModel.posts.removeObserver(itemsObserver)
        }
    }

    init {
        val dispatcher = TestCoroutineDispatcher()

        addListener(ArchTaskTestListener())
        addListener(CoroutineTaskTestListener(dispatcher))
        addListener(testListener)

        "emit post items on load" {
            viewModel.load()

            capturedItems.last()
                .first()
                .shouldBe(
                    PostItem(
                        id = 1,
                        title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                        body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto",
                        commentsSection = CommentsSection.Empty
                    )
                )

            capturedItems.last()
                .size
                .shouldBe(100)
        }

        "show loading placeholder and then loaded comments" {
            viewModel.load()

            viewModel.loadComments(1)

            // emitted first item over time
            val firstItem = capturedItems.flatMap { list -> list.filter { post -> post.id == 1 } }

            firstItem.first().commentsSection shouldBe CommentsSection.Empty
            firstItem[1].commentsSection shouldBe CommentsSection.Loading
            firstItem[2].commentsSection shouldBe CommentsSection.Loaded(
                listOf(
                    CommentItem(
                        postId = 1,
                        id = 1,
                        name = "id labore ex et quam laborum",
                        email = "Eliseo@gardner.biz",
                        body = "laudantium enim quasi est quidem magnam voluptate ipsam eos\n" +
                            "tempora quo necessitatibus\n" +
                            "dolor quam autem quasi\n" +
                            "reiciendis et nam sapiente accusantium"
                    ),
                    CommentItem(
                        postId = 1,
                        id = 2,
                        name = "quo vero reiciendis velit similique earum",
                        email = "Jayne_Kuhic@sydney.com",
                        body = "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et"
                    ),
                    CommentItem(
                        postId = 1,
                        id = 3,
                        name = "odio adipisci rerum aut animi",
                        email = "Nikita@garfield.biz",
                        body = "quia molestiae reprehenderit quasi aspernatur\naut expedita occaecati aliquam eveniet laudantium\nomnis quibusdam delectus saepe quia accusamus maiores nam est\ncum et ducimus et vero voluptates excepturi deleniti ratione"
                    ),
                    CommentItem(
                        postId = 1,
                        id = 4,
                        name = "alias odio sit",
                        email = "Lew@alysha.tv",
                        body = "non et atque\noccaecati deserunt quas accusantium unde odit nobis qui voluptatem\nquia voluptas consequuntur itaque dolor\net qui rerum deleniti ut occaecati"
                    ),
                    CommentItem(
                        postId = 1,
                        id = 5,
                        name = "vero eaque aliquid doloribus et culpa",
                        email = "Hayden@althea.biz",
                        body = "harum non quasi et ratione\ntempore iure ex voluptates in ratione\nharum architecto fugit inventore cupiditate\nvoluptates magni quo et"
                    )
                )
            )
        }

        "hide comments after loaded" {
            viewModel.load()
            viewModel.loadComments(1)
            viewModel.hideComments(1)

            // emitted first item over time
            val firstItem = capturedItems.flatMap { list -> list.filter { post -> post.id == 1 } }

            firstItem.first().commentsSection shouldBe CommentsSection.Empty
            firstItem[1].commentsSection shouldBe CommentsSection.Loading
            firstItem[2].commentsSection::class shouldBe CommentsSection.Loaded::class
            firstItem.last().commentsSection shouldBe CommentsSection.Empty
        }
    }
}