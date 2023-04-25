package com.example.csv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.opencsv.CSVReaderBuilder
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
        val csvFiles = listOf(
            "january23.csv",
            "April.csv"
        )
        for (filename in csvFiles) {
            val csvRows = mutableListOf<CsvRow>()
            val textView = findViewById<TextView>(R.id.tvCsv)
           // val sammy = findViewById<TextView>(R.id.SAM)
            val reader =
                CSVReaderBuilder(InputStreamReader(assets.open(filename)))
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


//        sams.forEach { row ->
//            val csvRow = CsvRow(
//                slNo = row[0],
//                regionalOffice = row[1],
//                district = row[2],
//                river = row[3],
//                codeNo = row[4],
//                sampleCollectionPoint = row[5],
//                doMgLit = row[6],
//                bdoMgLit = row[7],
//                totalColiformMpn100ml = row[8],
//                fecalColiformMpn100ml = row[9],
//                category = row[10]
//            )
//            csvRows.add(csvRow)
//        }
//        csvRows.forEach {
//            textView.append(
//                "${it.slNo} ${it.regionalOffice} ${it.district} ${it.river} ${it.codeNo} ${it.sampleCollectionPoint} ${it.doMgLit} ${it.bdoMgLit} ${it.totalColiformMpn100ml} ${it.fecalColiformMpn100ml} ${it.category}\n\n"
//            )
//
//        }
            csvRows.forEach {
                val data = CsvRow(
                    slNo = it.slNo,
                    regionalOffice = it.regionalOffice,
                    district = it.district,
                    river = it.river,
                    codeNo = it.codeNo,
                    sampleCollectionPoint = it.sampleCollectionPoint,
                    doMgLit = it.doMgLit,
                    bdoMgLit = it.bdoMgLit,
                    totalColiformMpn100ml = it.totalColiformMpn100ml,
                    fecalColiformMpn100ml = it.fecalColiformMpn100ml,
                    category = it.category
                )
                lifecycleScope.launchWhenCreated {
                    db.waterQualityDataDao().insert(data)
                }

                lifecycleScope.launchWhenCreated {
                    db.waterQualityDataDao().getAllData().forEach {
                        val data = CsvRow(
                            slNo = it.slNo,
                            regionalOffice = it.regionalOffice,
                            district = it.district,
                            river = it.river,
                            codeNo = it.codeNo,
                            sampleCollectionPoint = it.sampleCollectionPoint,
                            doMgLit = it.doMgLit,
                            bdoMgLit = it.bdoMgLit,
                            totalColiformMpn100ml = it.totalColiformMpn100ml,
                            fecalColiformMpn100ml = it.fecalColiformMpn100ml,
                            category = it.category
                        )

                    }


                }
                textView.append(
                    "${it.slNo} ${it.regionalOffice} ${it.district} ${it.river} ${it.codeNo} ${it.sampleCollectionPoint} ${it.doMgLit} ${it.bdoMgLit} ${it.totalColiformMpn100ml} ${it.fecalColiformMpn100ml} ${it.category}\n\n"
                )
                val searchView = findViewById<SearchView>(R.id.searchView)
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val filteredRows = csvRows.filter { row ->
                            row.slNo.contains(newText ?: "", ignoreCase = true)
                                    || row.regionalOffice.contains(newText ?: "", ignoreCase = true)
                                    || row.district.contains(newText ?: "", ignoreCase = true)
                                    || row.river.contains(newText ?: "", ignoreCase = true)
                                    || row.codeNo.contains(newText ?: "", ignoreCase = true)
                                    || row.sampleCollectionPoint.contains(newText ?: "", ignoreCase = true)
                                    || row.doMgLit.contains(newText ?: "", ignoreCase = true)
                                    || row.bdoMgLit.contains(newText ?: "", ignoreCase = true)
                                    || row.totalColiformMpn100ml.contains(newText ?: "", ignoreCase = true)
                                    || row.fecalColiformMpn100ml.contains(newText ?: "", ignoreCase = true)
                                    || row.category.contains(newText ?: "", ignoreCase = true)
                        }
                        textView.text = ""
                        filteredRows.forEach {
                            textView.append(
                                "${it.slNo} ${it.regionalOffice} ${it.district} ${it.river} ${it.codeNo} ${it.sampleCollectionPoint} ${it.doMgLit} ${it.bdoMgLit} ${it.totalColiformMpn100ml} ${it.fecalColiformMpn100ml} ${it.category}\n\n"
                            )
                        }
                        return true
                    }

                })


        }


    }

}
}