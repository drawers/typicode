package nz.co.tsongkha.typicode.post.presentation

import io.kotest.core.spec.style.StringSpec
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import nz.co.tsongkha.typicode.network.testNetworkModule
import nz.co.tsongkha.typicode.post.data.postsDataModule
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

        "it should" {
            println("goodbye")
            viewModel.load()
        }
    }
}