package project.spellit.repository

import android.content.ContentValues
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import project.spellit.activities.AddWordActivity

//TODO Реализовать через Room + посмотреть корректно работающую бд мб Room database
class DBHelper(
    context: AddWordActivity,
    name: String?,
    version: Int
) : SQLiteOpenHelper(context, name, null, version) {

    companion object {
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "words"


        const val KEY_ID = "_id"
        const val KEY_WORD = "word"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        try {
            p0?.execSQL(
                "CREATE TABLE" + TABLE_NAME + "(" + KEY_ID + "INTEGER PRIMARY KEY," +
                        KEY_WORD + "TEXT" + ")"
            )
        }
        catch (e: SQLException){
            e.printStackTrace()
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        try {
            p0?.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        }
        catch (e: SQLException){
            e.printStackTrace()
        }
        onCreate(p0)
    }

    fun insertWord(word: String){
        val cv = ContentValues()
        cv.put(KEY_WORD, word)
        try {
            val db = this.writableDatabase
            db.insert(TABLE_NAME, null, cv)
            db.close()
        }
        catch (e: SQLException){
            e.printStackTrace()
        }
    }

    fun getAllWords(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

}