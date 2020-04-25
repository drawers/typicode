package nz.co.tsongkha.typicode.post.presentation

import androidx.lifecycle.Observer
import io.kotest.core.spec.style.StringSpec
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import nz.co.tsongkha.typicode.network.testNetworkModule
import nz.co.tsongkha.typicode.post.data.postsDataModule
import nz.co.tsongkha.typicode.post.presentation.bento.PostItem
import toothpick.ktp.KTP

@ExperimentalCoroutinesApi
class MainViewModelTest : StringSpec() {

    init {
        val dispatcher = TestCoroutineDispatcher()

        addListener(ArchTaskTestListener())
        addListener(CoroutineTaskTestListener(dispatcher))

        val scope = KTP.openScope(this)
            .installModules(
                testNetworkModule,
                postsDataModule
            )

        val viewModel = MainViewModel()
        scope.inject(viewModel)

        val itemsObserver = mockk<Observer<List<PostItem>>>(relaxed = true)

        println("adding observer")

        "it should" {
            viewModel.posts.observeForever(itemsObserver)
            println("goodbye")
            viewModel.load()
            viewModel.posts.removeObserver(itemsObserver)
        }

        println("removing observer")

    }
}