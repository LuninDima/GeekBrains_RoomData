package ru.moondi.roomdata

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.moondi.roomdata.CityMapper.toContentValues
import ru.moondi.roomdata.CityMapper.toEntity
import ru.moondi.roomdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val historySource = HistorySource(contentResolver)
        historySource.query()

        binding.insert.setOnClickListener {
            historySource.insert(HistoryEntity(999, "Багамы", 35))
        }
        binding.get.setOnClickListener {
            historySource.getHistory()
        }
        binding.getByPosition.setOnClickListener {
            historySource.getCityByPosition(1)
        }
        binding.update.setOnClickListener {
            historySource.update(HistoryEntity(1, "Гонолулу", 53))
        }
        binding.delete.setOnClickListener {
            historySource.delete(HistoryEntity(0))
        }
    }
}

