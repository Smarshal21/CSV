package com.example.csv

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CsvRow::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun waterQualityDataDao(): WaterQualityDataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "water_quality_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
