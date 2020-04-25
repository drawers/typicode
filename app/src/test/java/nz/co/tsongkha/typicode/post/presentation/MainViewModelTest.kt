package nz.co.tsongkha.typicode.post.presentation

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase

class MainViewModelTest : StringSpec() {

    val listener: TestListener = object : TestListener {

        override suspend fun beforeSpec(spec: Spec) {
            super.beforeSpec(spec)

            //instant tasks in a coroutine

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

            //reset main
        }

        override suspend fun beforeTest(testCase: TestCase) {
            super.beforeTest(testCase)
            println("Hello")
        }
    }

    init {
        addListener(listener)

        "it should" {
            println("goodbye")
        }
    }
}