package com.kp.beautytips.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kp.beautytips.model.DiaryEntry

class DiaryDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "beauty_diary.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_DIARY = "diary_entries"
        const val COLUMN_ID = "id"
        const val COLUMN_DATE = "entry_date"
        const val COLUMN_TITLE = "title"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_IMAGE_PATH = "image_path"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE " + TABLE_DIARY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_CONTENT + " TEXT,"
                + COLUMN_IMAGE_PATH + " TEXT" + ")")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_DIARY")
        onCreate(db)
    }

    fun insertEntry(date: String, title: String, content: String, imagePath: String?): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_DATE, date)
            put(COLUMN_TITLE, title)
            put(COLUMN_CONTENT, content)
            put(COLUMN_IMAGE_PATH, imagePath)
        }
        return db.insert(TABLE_DIARY, null, values)
    }

    fun updateEntry(id: Int, date: String, title: String, content: String, imagePath: String?): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_DATE, date)
            put(COLUMN_TITLE, title)
            put(COLUMN_CONTENT, content)
            put(COLUMN_IMAGE_PATH, imagePath)
        }
        return db.update(TABLE_DIARY, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun deleteEntry(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_DIARY, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun getAllEntries(): List<DiaryEntry> {
        val entries = ArrayList<DiaryEntry>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_DIARY ORDER BY $COLUMN_DATE DESC, $COLUMN_ID DESC", null)
        
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
                val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
                val imagePath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_PATH))
                entries.add(DiaryEntry(id, date, title, content, imagePath))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return entries
    }
}
