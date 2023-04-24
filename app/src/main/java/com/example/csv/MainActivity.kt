package com.example.csv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.opencsv.CSVReaderBuilder
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val csvRows = mutableListOf<CsvRow>()
        val textView = findViewById<TextView>(R.id.tvCsv)
        val reader = CSVReaderBuilder(InputStreamReader(assets.open("january23.csv")))
            .withSkipLines(1) // skip header row
            .build()

        reader.forEach { row ->
            val csvRow = CsvRow(
                slNo = row[0],
                regionalOffice = row[1],
                district = row[2],
                river = row[3],
                codeNo = row[4],
                sampleCollectionPoint = row[5],
                doMgLit = row[6],
                bdoMgLit = row[7],
                totalColiformMpn100ml = row[8],
                fecalColiformMpn100ml = row[9],
                category = row[10]
            )
            csvRows.add(csvRow)
        }
        csvRows.forEach {
            textView.append(
                "${it.slNo} ${it.regionalOffice} ${it.district} ${it.river} ${it.codeNo} ${it.sampleCollectionPoint} ${it.doMgLit} ${it.bdoMgLit} ${it.totalColiformMpn100ml} ${it.fecalColiformMpn100ml} ${it.category}\n\n"
            )

        }
    }
}