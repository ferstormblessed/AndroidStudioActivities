package com.example.actividad2_almacenamientolocal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context?): SQLiteOpenHelper(context, DB_FILE, null, 1){

    companion object{
        private const val DB_FILE = "almacenamiento.db"
        private const val TABLE = "almacenamiento"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME= "name"
        private const val COLUMN_HOBBY = "hobby"

        private const val TABLE2 = "saluditos"
        private const val COLUMN_ID2 = "id"
        private const val COLUMN_SALUDO = "saludo"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE(" +
                "$COLUMN_ID INTEGER PRIMARY KEY," +
                "$COLUMN_NAME  TEXT," +
                "$COLUMN_HOBBY TEXT)"

        val query2 = "CREATE TABLE $TABLE2 (" +
                "$COLUMN_ID2 INTEGER PRIMARY KEY," +
                "$COLUMN_SALUDO TEXT)"

        db?.execSQL(query)
        db?.execSQL(query2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val query = "DROP TABLE IF EXISTS ?"
        val args = arrayOf(TABLE)
        val args2 = arrayOf(TABLE2)

        db?.execSQL(query,args)
        db?.execSQL(query,args2)
        onCreate(db)
    }

    //guardar datos nuevos
    fun save(name: String?, hobby: String?){
        val valores = ContentValues()
        valores.put(COLUMN_NAME, name)
        valores.put(COLUMN_HOBBY, hobby)

        writableDatabase.insert(TABLE, null, valores)
    }

    fun saveSaludo(saludo: String?){
        val valores = ContentValues()
        valores.put(COLUMN_SALUDO, saludo)

        writableDatabase.insert(TABLE2, null, valores)
    }

    fun delete(name: String?): Int {
        val clause = "$COLUMN_NAME = ?"
        val args = arrayOf(name)
        return writableDatabase.delete(TABLE, clause, args)
        //return writableDatabase.delete(TABLE, clause, args)
    }

    //buscar
    fun findHobby() : String{
        val cursor = readableDatabase.query(TABLE,null, null, null,null,null,null)

        var result = ""

        if(cursor.moveToFirst()){
            result = cursor.getString(2)
        }

        cursor.close()
        return result
    }

    fun findHobby(name: String?) : String{
        val clause = "$COLUMN_NAME = ?"
        val args = arrayOf(name)

        val cursor = readableDatabase.query(TABLE,null, clause, args,null,null,null)

        var result = ""

        if(cursor.moveToFirst()){
            result = cursor.getString(2)
        }

        cursor.close()
        return result
    }

    fun findSaludo(saludoNumber: Int) : String{
        val clause = "$COLUMN_ID2 = ?"
        val args = arrayOf(saludoNumber.toString())
        val cursor = readableDatabase.query(TABLE2,null, clause, args,null,null,null)

        var result = ""

        if(cursor.moveToFirst()){
            result = cursor.getString(1)
        }

        cursor.close()
        return result
    }

    fun updateHobby(oldHobby: String?, newHobby: String?){
        val values = ContentValues()
        values.put(COLUMN_HOBBY, newHobby)

        val clause = "$COLUMN_HOBBY = ?"
        val args = arrayOf(oldHobby)

        writableDatabase.update(TABLE, values, clause, args)
    }

}