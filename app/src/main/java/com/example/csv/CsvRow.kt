package com.example.csv

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_quality_data")
data class CsvRow(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val slNo: String,
    val regionalOffice: String,
    val district: String,
    val river: String,
    val codeNo: String,
    val sampleCollectionPoint: String,
    val doMgLit: String,
    val bdoMgLit: String,
    val totalColiformMpn100ml: String,
    val fecalColiformMpn100ml: String,
    val category: String
)

