package ru.moondi.roomdata

import android.content.ContentResolver
import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import ru.moondi.roomdata.CityMapper.toContentValues
import ru.moondi.roomdata.CityMapper.toEntity
import java.text.FieldPosition

private const val ENTITY_PATH = "HistoryEntity"
private var authorities: String = "moondi.provider"

class HistorySource(
    private val contentResolver: ContentResolver
) {

    private var cursor: Cursor? = null

    fun query() {
        cursor = contentResolver.query(HISTORY_URI, null, null, null, null)
    }

    fun getHistory() {

        cursor?.let { cursor ->
            for (i in 0..cursor.count) {

                if (cursor.moveToPosition(i)) {

                    toEntity(cursor)
                }
            }
        }
        cursor?.close()
    }

    fun getCityByPosition(position: Int): HistoryEntity {
        return if (cursor == null) {
            HistoryEntity()
        } else {
            cursor?.moveToPosition(position)
            toEntity(cursor!!)
        }
    }


    fun insert(entity: HistoryEntity) {
            contentResolver.insert(HISTORY_URI, toContentValues(entity))
        query()
    }


    fun update(entity: HistoryEntity) {
        val uri: Uri = ContentUris.withAppendedId(HISTORY_URI, entity.id)
        contentResolver.update(uri, toContentValues(entity), null, null)
        query()
    }

    fun delete(entity: HistoryEntity) {
        val uri: Uri = ContentUris.withAppendedId(HISTORY_URI, entity.id)
        contentResolver.delete(uri, null, null)
        query()
    }

    companion object {
        private val HISTORY_URI: Uri =
            Uri.parse("content://moondi.provider/HistoryEntity")
    }
}