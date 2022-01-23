package project.spellit.activities

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
    name: String?,
    version: Int
) : SQLiteOpenHelper(context, name, null, version) {

    val DATABASE_VERSION = 1
    val TABLE_CONTRACT = "contracts"

    val KEY_ID = "_id"
    val KEY_WORD = "word"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table" + TABLE_CONTRACT + "(" + KEY_ID + "integer primary key," +
                KEY_WORD + "text" + ")")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists" + TABLE_CONTRACT)

        onCreate(p0)
    }

}