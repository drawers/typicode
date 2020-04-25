package nz.co.tsongkha.typicode.post.presentation

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import nz.co.tsongkha.typicode.network.testNetworkModule
import nz.co.tsongkha.typicode.post.data.postsDataModule
import toothpick.ktp.KTP

@ExperimentalCoroutinesApi
class MainViewModelTest : StringSpec() {

    val listener: TestListener = object : TestListener {

        override suspend fun beforeSpec(spec: Spec) {
            super.beforeSpec(spec)

            //instant tasks in a coroutine

            val testDispatcher = TestCoroutineDispatcher()
            Dispatchers.setMain(testDispatcher)

            ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) {
                    runnable.run()
                }

                override fun postToMainThread(runnable: Runnable) {
                    runnable.run()
                }

                override fun isMainThread(): Boolean {
                    return true
                }
            })
        }

        override suspend fun afterSpec(spec: Spec) {
            super.afterSpec(spec)
            ArchTaskExecutor.getInstance().setDelegate(null)
            Dispatchers.resetMain()
        }

        override suspend fun beforeTest(testCase: TestCase) {
            super.beforeTest(testCase)
            println("Hello")
        }
    }

    init {
        addListener(listener)

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