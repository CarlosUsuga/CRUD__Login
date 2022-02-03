package com.example.crudlogin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class UsuarioCRUD(context: Context) {

    private var helper: DataBaseHelper? = null

    init {
        helper = DataBaseHelper(context)
    }

    fun nuevoUsuario(item: Usuario) {

        //abrir DB en modo escritura
        val db: SQLiteDatabase = helper?.writableDatabase!!

        //mapeo de columnas con valores a insertar
        val values = ContentValues()
        values.put(UsuarioContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(UsuarioContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)
        values.put(UsuarioContract.Companion.Entrada.COLUMNA_APELLIDO, item.apellido)
        values.put(UsuarioContract.Companion.Entrada.COLUMNA_CARGO, item.cargo)
        values.put(UsuarioContract.Companion.Entrada.COLUMNA_CORREO, item.correo)
        values.put(UsuarioContract.Companion.Entrada.COLUMNA_PASSWORD, item.password)

        //Insertar un nuevo usuario en la tabla
        val newRowId = db.insert(UsuarioContract.Companion.Entrada.NOMBRE_TABLA, null, values)

        db.close()
    }

    fun getUsuarios(): ArrayList<Usuario> {
        val items: ArrayList<Usuario> = ArrayList()

        //abrir DB en modo lectura
        val db: SQLiteDatabase = helper?.readableDatabase!!

        //Especificar columnas que quiero ver
        val columnas = arrayOf(
            UsuarioContract.Companion.Entrada.COLUMNA_ID,
            UsuarioContract.Companion.Entrada.COLUMNA_NOMBRE,
            UsuarioContract.Companion.Entrada.COLUMNA_APELLIDO,
            UsuarioContract.Companion.Entrada.COLUMNA_CARGO,
            UsuarioContract.Companion.Entrada.COLUMNA_CORREO,
            UsuarioContract.Companion.Entrada.COLUMNA_PASSWORD
        )

        //crear cursos para recorrer la tabla
        val c: Cursor = db.query(
            UsuarioContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            null,
            null,
            null,
            null,
            null
        )

        //hacer el recorrido del cursor
        while (c.moveToNext()) {
            items.add(
                Usuario(
                    c.getString(c.getColumnIndexOrThrow(UsuarioContract.Companion.Entrada.COLUMNA_ID)),
                    (UsuarioContract.Companion.Entrada.COLUMNA_NOMBRE),
                    (UsuarioContract.Companion.Entrada.COLUMNA_APELLIDO),
                    (UsuarioContract.Companion.Entrada.COLUMNA_CARGO),
                    (UsuarioContract.Companion.Entrada.COLUMNA_CORREO),
                    (UsuarioContract.Companion.Entrada.COLUMNA_PASSWORD)
                )
            )
        }

        //cerrar db
        db.close()
        return items
    }


    fun getUsuario(id: String?): Usuario{
        var item:Usuario? = null

        val db: SQLiteDatabase = helper?.readableDatabase!!

        val columnas = arrayOf(UsuarioContract.Companion.Entrada.COLUMNA_ID,
            UsuarioContract.Companion.Entrada.COLUMNA_NOMBRE, UsuarioContract.Companion.Entrada.COLUMNA_APELLIDO,
            UsuarioContract.Companion.Entrada.COLUMNA_CARGO, UsuarioContract.Companion.Entrada.COLUMNA_CORREO,UsuarioContract.Companion.Entrada.COLUMNA_PASSWORD)

        val c:Cursor = db.query(UsuarioContract.Companion.Entrada.NOMBRE_TABLA, columnas, " id = ?", arrayOf(id), null, null, null)

        while (c.moveToNext()){
            item = Usuario(c.getString(c.getColumnIndexOrThrow(UsuarioContract.Companion.Entrada.COLUMNA_ID)),
                c.getString(c.getColumnIndexOrThrow(UsuarioContract.Companion.Entrada.COLUMNA_NOMBRE)),
                c.getString(c.getColumnIndexOrThrow(UsuarioContract.Companion.Entrada.COLUMNA_APELLIDO)),
                c.getString(c.getColumnIndexOrThrow(UsuarioContract.Companion.Entrada.COLUMNA_CARGO)),
                c.getString(c.getColumnIndexOrThrow(UsuarioContract.Companion.Entrada.COLUMNA_CORREO)),
                c.getString(c.getColumnIndexOrThrow(UsuarioContract.Companion.Entrada.COLUMNA_PASSWORD))

            )
        }

        c.close()
        return item!!;
    }

    fun updateUsuario(item: Usuario){

        val db: SQLiteDatabase = helper?.writableDatabase!!

        val values = ContentValues()
        values.put(UsuarioContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(UsuarioContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)
        values.put(UsuarioContract.Companion.Entrada.COLUMNA_APELLIDO, item.apellido)
        values.put(UsuarioContract.Companion.Entrada.COLUMNA_CARGO, item.cargo)
        values.put(UsuarioContract.Companion.Entrada.COLUMNA_CORREO, item.correo)
        values.put(UsuarioContract.Companion.Entrada.COLUMNA_PASSWORD, item.password)

        db.update(
            UsuarioContract.Companion.Entrada.NOMBRE_TABLA,
            values, " id = ?",
            arrayOf(item.id)
        )
        db.close()
    }

    fun deleteUsuario(item: Usuario){
        val db: SQLiteDatabase = helper?.writableDatabase!!

        db.delete(UsuarioContract.Companion.Entrada.NOMBRE_TABLA,
            " id = ?", arrayOf(item.id))

        db.close()
    }

    fun login(item: Usuario): Unit{

        var item:Usuario? = null

        val db: SQLiteDatabase = helper?.readableDatabase!!

        val columnas = arrayOf(
            UsuarioContract.Companion.Entrada.COLUMNA_CORREO,
            UsuarioContract.Companion.Entrada.COLUMNA_PASSWORD
        )
        val c:Cursor = db.query(UsuarioContract.Companion.Entrada.NOMBRE_TABLA, columnas, " id = ?", arrayOf(""), null, null, null)

    }

}