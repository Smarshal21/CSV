package com.example.csv

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WaterQualityDataDao {
    @Insert
   suspend fun insert(data: CsvRow)

    @Query("SELECT * FROM water_quality_data")
    suspend  fun getAllData(): List<CsvRow>
    
}

