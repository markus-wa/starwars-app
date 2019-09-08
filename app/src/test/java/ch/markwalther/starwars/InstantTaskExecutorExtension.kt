package ch.markwalther.starwars

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * This extension allow us to run LiveData etc. synchronously.
 */
class InstantTaskExecutorExtension : BeforeEachCallback, AfterEachCallback {

	override fun beforeEach(context: ExtensionContext) {
		ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
			override fun executeOnMainThread(runnable: Runnable) = runnable.run()

			override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

			override fun postToMainThread(runnable: Runnable) = runnable.run()

			override fun isMainThread() = true
		})
	}

	override fun afterEach(context: ExtensionContext?) {
		ArchTaskExecutor.getInstance().setDelegate(null)
	}

}
