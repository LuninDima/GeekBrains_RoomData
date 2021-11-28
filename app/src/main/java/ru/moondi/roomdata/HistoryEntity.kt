package ru.moondi.roomdata

import android.content.ContentValues
import android.database.Cursor

data class HistoryEntity(
    val id: Long = 0,
    val city: String = "",
    val temperature: Int = 0,
)

object CityMapper {
    private const val ID = "id"
    private const val CITY = "city"
    private const val TEMPERATURE = "temperature"

    fun toEntity(cursor: Cursor): HistoryEntity {
        return HistoryEntity(
            cursor.getLong(cursor.getColumnIndex(ID)),
            cursor.getString(cursor.getColumnIndex(CITY)),
            cursor.getInt(cursor.getColumnIndex(TEMPERATURE)),
        )
    }

    fun toContentValues(student: HistoryEntity): ContentValues {
        return ContentValues().apply {
            put(ID, student.id)
            put(CITY, student.city)
            put(TEMPERATURE, student.temperature)
        }
    }
}