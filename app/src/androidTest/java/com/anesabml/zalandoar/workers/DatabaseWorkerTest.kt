package com.anesabml.zalandoar.workers

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker.Result
import androidx.work.WorkManager
import androidx.work.testing.TestListenableWorkerBuilder
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseWorkerTest {
    private lateinit var context: Context
    private lateinit var workManager: WorkManager

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        workManager = WorkManager.getInstance(context)
    }

    @Test
    fun testRefreshMainDataWork() {
        // Get the ListenableWorker
        val worker = TestListenableWorkerBuilder<DatabaseWorker>(context).build()

        // Start the work synchronously
        val result = worker.startWork().get()

        assertThat(result).isEqualTo(Result.success())
    }
}