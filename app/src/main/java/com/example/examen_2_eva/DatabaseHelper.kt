package com.example.examen_2_eva


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast



// Clase DatabaseHelper
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "juegosComprados.db"
        private const val TABLA_VIDEOJUEGOS = "tabla_videojuegos"
        private const val KEY_ID = "id"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_VALORACION = "valoracion"
        private const val COLUMN_EMPRESA = "empresa"
        private const val COLUMN_AÑO_LANZAMIENTO = "año_lanzamiento"
        private const val CREATE_TABLA_VIDEOJUEGOS = "CREATE TABLE $TABLA_VIDEOJUEGOS (" +
                "$KEY_ID INTEGER PRIMARY KEY," +
                " $COLUMN_NOMBRE TEXT," +
                " $COLUMN_EMPRESA TEXT," +
                " $COLUMN_VALORACION FLOAT," +
                " $COLUMN_AÑO_LANZAMIENTO INTEGER)"

    }

    override fun onCreate(db: SQLiteDatabase) {
       //val createTable = "CREATE TABLE $TABLA_XXX ($KEY_ID INTEGER PRIMARY KEY, $COLUMN_XXX TEXT, $COLUMN_YYY INTEGER)"
        //db.execSQL(createTable)
        db.execSQL(CREATE_TABLA_VIDEOJUEGOS)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_VIDEOJUEGOS")
        onCreate(db)
    }

    fun escribir(videojuegos:ArrayList<Videojuego>) {
        val db = this.writableDatabase
        for(videojuego in videojuegos){
            val values = ContentValues().apply {
                put(COLUMN_NOMBRE, videojuego.getNombre())
                put(COLUMN_VALORACION,videojuego.getValoracion())
                put(COLUMN_EMPRESA, videojuego.getEmpresa())
                put(COLUMN_AÑO_LANZAMIENTO, videojuego.getAño_lanzamiento())
            }
            val id= db.insert(TABLA_VIDEOJUEGOS, null, values)
        }


        db.close()
    }


    @SuppressLint("Range")
    fun lectura(): ArrayList<Videojuego> {
        val videojuegos = ArrayList<Videojuego>()
        val selectQuery = "SELECT * FROM $TABLA_VIDEOJUEGOS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
               // val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val año_lanzamiento = cursor.getInt(cursor.getColumnIndex(COLUMN_AÑO_LANZAMIENTO))
                val valoracion= cursor.getFloat(cursor.getColumnIndex(COLUMN_VALORACION))
                val empresa = cursor.getString(cursor.getColumnIndex(COLUMN_EMPRESA))
                val videojuego=Videojuego(nombre,valoracion,empresa,año_lanzamiento)
                videojuegos.add(videojuego)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return videojuegos
    }

}
