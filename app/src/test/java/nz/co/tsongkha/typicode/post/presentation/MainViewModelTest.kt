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

            itemsObserver = mockk<Observer<List<PostItem>>>(relaxed = true)
            capturedItems = mutableListOf<List<PostItem>>()
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

        "it should emit post items on load" {
            viewModel.load()

            capturedItems.last()
                .first()
                .shouldBe(
                    PostItem(
                        id = 1,
                        title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                        body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto",
                        commentsSection = CommentsSection.EMPTY
                    )
                )

            capturedItems.last()
                .size
                .shouldBe(100)

            viewModel.posts.removeObserver(itemsObserver)
        }
    }
}