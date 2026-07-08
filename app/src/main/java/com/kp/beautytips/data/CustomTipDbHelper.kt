package com.kp.beautytips.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kp.beautytips.model.CustomTip

class CustomTipDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "custom_tips.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_CUSTOM_TIPS = "custom_tips"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DURATION = "duration"
        const val COLUMN_DETAILS = "details"
        const val COLUMN_CATEGORY = "category"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE " + TABLE_CUSTOM_TIPS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_DURATION + " TEXT,"
                + COLUMN_DETAILS + " TEXT,"
                + COLUMN_CATEGORY + " TEXT" + ")")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CUSTOM_TIPS")
        onCreate(db)
    }

    fun insertTip(title: String, duration: String, details: String, category: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_DURATION, duration)
            put(COLUMN_DETAILS, details)
            put(COLUMN_CATEGORY, category)
        }
        return db.insert(TABLE_CUSTOM_TIPS, null, values)
    }

    fun updateTip(id: Int, title: String, duration: String, details: String, category: String): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_DURATION, duration)
            put(COLUMN_DETAILS, details)
            put(COLUMN_CATEGORY, category)
        }
        return db.update(TABLE_CUSTOM_TIPS, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun deleteTip(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_CUSTOM_TIPS, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun getAllTips(): List<CustomTip> {
        val tips = ArrayList<CustomTip>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_CUSTOM_TIPS ORDER BY $COLUMN_ID DESC", null)
        
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
                val duration = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DURATION))
                val details = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DETAILS))
                val category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY))
                tips.add(CustomTip(id, title, duration, details, category))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return tips
    }
}
