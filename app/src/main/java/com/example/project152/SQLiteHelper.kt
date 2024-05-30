package com.example.project152

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, NAMA_DATABASE, null, 1) {
    companion object {
        private const val NAMA_DATABASE = "localeapp.db"
        private const val NAMA_TABLE = "tborder"

        private const val COL_1 = "ID"
        private const val COL_2 = "BRAND"
        private const val COL_3 = "TYPE"
        private const val COL_4 = "SEX"
        private const val COL_5 = "PRICE"
        private const val COL_6 = "SIZE"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $NAMA_TABLE (" +
                "$COL_1 INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_2 TEXT," +
                "$COL_3 TEXT," +
                "$COL_4 TEXT," +
                "$COL_5 TEXT," +
                "$COL_6 TEXT" +
                ")")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $NAMA_TABLE")
        onCreate(db)
    }

    fun insertData(brand: String, type: String, sex: String, price: String, size: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_2, brand)
        values.put(COL_3, type)
        values.put(COL_4, sex)
        values.put(COL_5, price)
        values.put(COL_6, size)
        val result = db.insert(NAMA_TABLE, null, values)
        return result != -1L
    }

    fun getDataAll(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $NAMA_TABLE", null)
    }

    fun updateData(id: String, brand: String, type: String, sex: String, price: String, size: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COL_1, id)
        values.put(COL_2, brand)
        values.put(COL_3, type)
        values.put(COL_4, sex)
        values.put(COL_5, price)
        values.put(COL_6, size)

        db.update(NAMA_TABLE, values, "$COL_1 = ?", arrayOf(id))
        return true
    }

    fun deleteData(id: String): Int {
        val db = this.writableDatabase
        return db.delete(NAMA_TABLE, "$COL_1 = ?", arrayOf(id))
    }
}