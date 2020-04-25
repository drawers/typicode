package nz.co.tsongkha.typicode.post.presentation

import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.Spec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@ExperimentalCoroutinesApi
class CoroutineTaskTestListener(val testCoroutineDispatcher: TestCoroutineDispatcher) : TestListener {

    override suspend fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        val testDispatcher = testCoroutineDispatcher
        Dispatchers.setMain(testDispatcher)
    }

    override suspend fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        Dispatchers.resetMain()
    }
}