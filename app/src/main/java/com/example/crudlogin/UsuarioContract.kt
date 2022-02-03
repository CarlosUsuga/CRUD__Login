package com.example.crudlogin

import android.provider.BaseColumns


class UsuarioContract {

    companion object{

        val VERSION = 1
        class Entrada: BaseColumns {
            companion object{
                val NOMBRE_TABLA = "Usuario"
                val COLUMNA_ID = "id"
                val COLUMNA_NOMBRE = "nombre"
                val COLUMNA_APELLIDO = "apellido"
                val COLUMNA_CARGO = "cargo"
                val COLUMNA_CORREO = "correo"
                val COLUMNA_PASSWORD = "password"
            }
        }
    }
}