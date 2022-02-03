package com.example.crudlogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class NuevoUsuarioActivity : AppCompatActivity() {

    var crud:UsuarioCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_usuario)

        val id = findViewById<EditText>(R.id.etID)
        val nombre = findViewById<EditText>(R.id.etNombre)
        val apellido = findViewById<EditText>(R.id.etApellidos)
        val cargo = findViewById<EditText>(R.id.etCargo)
        val correo = findViewById<EditText>(R.id.etCorreo)
        val password = findViewById<EditText>(R.id.etPassword)
        val btnNuevo = findViewById<Button>(R.id.btnNuevo)

        crud = UsuarioCRUD(this)

        btnNuevo.setOnClickListener{
            crud?.nuevoUsuario(Usuario(id.text.toString(), nombre.text.toString(),
                apellido.text.toString(),cargo.text.toString(),correo.text.toString(), password.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))
        }


    }
}