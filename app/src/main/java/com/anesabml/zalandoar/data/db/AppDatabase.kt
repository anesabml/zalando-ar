package com.anesabml.zalandoar.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.anesabml.zalandoar.data.entity.ProductEntity
import com.anesabml.zalandoar.utils.DateConverter
import com.anesabml.zalandoar.utils.ListConverter
import com.anesabml.zalandoar.utils.ProductCategoryConverter
import com.anesabml.zalandoar.workers.DatabaseWorker

@Database(entities = [ProductEntity::class], version = 1)
@TypeConverters(ListConverter::class, DateConverter::class, ProductCategoryConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        private const val DATABASE_NAME = "zalando.db"

        @Volatile
        private var instance: AppDatabase? = null

        private fun create(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val databaseWorkerRequest =
                                OneTimeWorkRequestBuilder<DatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(databaseWorkerRequest)
                        }
                    }
                )
                .build()

        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                (instance ?: create(context)).also { instance = it }
            }
    }
}