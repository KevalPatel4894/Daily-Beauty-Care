package com.kp.beautytips.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kp.beautytips.model.RoutineModel
import com.kp.beautytips.model.RoutineStep
import org.json.JSONArray
import org.json.JSONObject

class RoutineDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "routine_builder.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_ROUTINES = "routines"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_TYPE = "type"
        const val COLUMN_STEPS_JSON = "steps_json"

        fun stepsToJson(steps: List<RoutineStep>): String {
            val array = JSONArray()
            for (step in steps) {
                val obj = JSONObject()
                obj.put("stepName", step.stepName)
                obj.put("description", step.description)
                obj.put("timerSeconds", step.timerSeconds)
                array.put(obj)
            }
            return array.toString()
        }

        fun jsonToSteps(jsonStr: String?): List<RoutineStep> {
            if (jsonStr.isNullOrEmpty()) return emptyList()
            val steps = ArrayList<RoutineStep>()
            try {
                val array = JSONArray(jsonStr)
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    val name = obj.optString("stepName", "")
                    val desc = obj.optString("description", "")
                    val timer = obj.optInt("timerSeconds", 0)
                    steps.add(RoutineStep(name, desc, timer))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return steps
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE " + TABLE_ROUTINES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_TYPE + " TEXT,"
                + COLUMN_STEPS_JSON + " TEXT" + ")")
        db.execSQL(createTable)

        // Seed default Morning and Evening routines
        seedDefaultRoutines(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ROUTINES")
        onCreate(db)
    }

    private fun seedDefaultRoutines(db: SQLiteDatabase) {
        // Morning Routine
        val morningSteps = listOf(
            RoutineStep("Gentle Cleanser", "Wash face with tepid water and mild cleanser", 60),
            RoutineStep("Hydrating Toner", "Pat toner gently onto face and neck", 30),
            RoutineStep("Vitamin C Serum", "Apply 3-4 drops for daytime antioxidant protection", 45),
            RoutineStep("Moisturizer", "Lock in moisture with a lightweight lotion", 45),
            RoutineStep("Sunscreen (SPF 50)", "Apply broad spectrum sunscreen generously", 60)
        )
        insertRoutineWithDb(db, "Morning Glow Routine", "Morning", morningSteps)

        // Night Routine
        val nightSteps = listOf(
            RoutineStep("Double Cleanse", "Remove oil and makeup with cleansing oil, then water cleanser", 120),
            RoutineStep("Exfoliate / Mask", "Apply soothing natural mask or gentle exfoliant", 300),
            RoutineStep("Eye Cream", "Gently tap eye cream along orbital bone", 30),
            RoutineStep("Nourishing Night Cream", "Apply rich moisturizer or overnight mask", 60)
        )
        insertRoutineWithDb(db, "Night Repair Routine", "Night", nightSteps)
    }

    private fun insertRoutineWithDb(db: SQLiteDatabase, title: String, type: String, steps: List<RoutineStep>): Long {
        val values = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_TYPE, type)
            put(COLUMN_STEPS_JSON, stepsToJson(steps))
        }
        return db.insert(TABLE_ROUTINES, null, values)
    }

    fun insertRoutine(title: String, type: String, steps: List<RoutineStep>): Long {
        val db = this.writableDatabase
        return insertRoutineWithDb(db, title, type, steps)
    }

    fun updateRoutine(id: Int, title: String, type: String, steps: List<RoutineStep>): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_TYPE, type)
            put(COLUMN_STEPS_JSON, stepsToJson(steps))
        }
        return db.update(TABLE_ROUTINES, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun deleteRoutine(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_ROUTINES, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun getAllRoutines(): List<RoutineModel> {
        val list = ArrayList<RoutineModel>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_ROUTINES ORDER BY $COLUMN_ID ASC", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
                val type = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE))
                val json = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STEPS_JSON))
                list.add(RoutineModel(id, title, type, jsonToSteps(json)))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}
