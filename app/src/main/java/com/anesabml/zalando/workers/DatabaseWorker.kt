package com.anesabml.zalando.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.anesabml.zalando.data.db.AppDatabase
import com.anesabml.zalando.data.entity.ProductEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import timber.log.Timber

class DatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        return try {
            applicationContext.assets.open(PRODUCTS_DATA_FILENAME).bufferedReader()
                .use { bufferedReader ->
                    val products =
                        Json { allowStructuredMapKeys = true }
                            .decodeFromString<List<ProductEntity>>(bufferedReader.readText())

                    val database = AppDatabase.getInstance(applicationContext)
                    database.productDao().insertAll(products)

                    Result.success()
                }
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure()
        }
    }

    companion object {
        const val PRODUCTS_DATA_FILENAME = "products.json"
    }
}