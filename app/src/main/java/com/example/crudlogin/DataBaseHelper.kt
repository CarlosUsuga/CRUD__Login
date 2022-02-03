package com.example.crudlogin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, UsuarioContract.Companion.Entrada.NOMBRE_TABLA, null, UsuarioContract.Companion.VERSION) {

    companion object{
        val CREATE_TABLEUSUARIO = "CREATE TABLE " + UsuarioContract.Companion.Entrada.NOMBRE_TABLA +
                " (" + UsuarioContract.Companion.Entrada.COLUMNA_ID + " TEXT PRIMARY KEY, " + UsuarioContract.Companion.Entrada.COLUMNA_NOMBRE + " TEXT, " +
                UsuarioContract.Companion.Entrada.COLUMNA_APELLIDO + " TEXT, " + UsuarioContract.Companion.Entrada.COLUMNA_CARGO + " TEXT, " +
                UsuarioContract.Companion.Entrada.COLUMNA_CORREO + " TEXT, " + UsuarioContract.Companion.Entrada.COLUMNA_PASSWORD + " TEXT )"

        val REMOVE_TABLEUSUARIO = "DROP TABLE " + UsuarioContract.Companion.Entrada.NOMBRE_TABLA
    }
    override fun onCreate(db: SQLiteDatabase) {
        db?.execSQL(CREATE_TABLEUSUARIO)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db?.execSQL(REMOVE_TABLEUSUARIO)
        onCreate(db)
    }

}
