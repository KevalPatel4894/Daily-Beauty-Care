package com.kp.beautytips.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class RemedyNotesDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "remedy_notes.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NOTES = "remedy_notes"
        const val COLUMN_ID = "id"
        const val COLUMN_TIP_TITLE = "tip_title"
        const val COLUMN_CONTENT = "notes_content"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE " + TABLE_NOTES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TIP_TITLE + " TEXT UNIQUE,"
                + COLUMN_CONTENT + " TEXT" + ")")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NOTES")
        onCreate(db)
    }

    fun getNoteForTip(tipTitle: String): String? {
        val db = this.readableDatabase
        var note: String? = null
        val cursor = db.rawQuery("SELECT $COLUMN_CONTENT FROM $TABLE_NOTES WHERE $COLUMN_TIP_TITLE = ?", arrayOf(tipTitle))
        if (cursor.moveToFirst()) {
            note = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
        }
        cursor.close()
        return note
    }

    fun saveNoteForTip(tipTitle: String, noteContent: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TIP_TITLE, tipTitle)
            put(COLUMN_CONTENT, noteContent)
        }
        return db.insertWithOnConflict(
            TABLE_NOTES,
            null,
            values,
            SQLiteDatabase.CONFLICT_REPLACE
        )
    }

    fun deleteNoteForTip(tipTitle: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NOTES, "$COLUMN_TIP_TITLE = ?", arrayOf(tipTitle))
    }
}
