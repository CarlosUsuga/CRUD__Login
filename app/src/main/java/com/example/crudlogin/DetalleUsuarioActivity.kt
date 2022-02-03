package com.example.crudlogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleUsuarioActivity : AppCompatActivity() {

    var crud:UsuarioCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_usuario)

        val id = findViewById<EditText>(R.id.etID)
        val nombre = findViewById<EditText>(R.id.etNombre)
        val apellido = findViewById<EditText>(R.id.etApellidos)
        val cargo = findViewById<EditText>(R.id.etCargo)
        val correo = findViewById<EditText>(R.id.etCorreo)
        val password = findViewById<EditText>(R.id.etPassword)
        val btnActualizar = findViewById<Button>(R.id.btnActualizar)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)

        val index = intent.getStringExtra("ID")

        crud = UsuarioCRUD(this)

        val usuario = crud?.getUsuario(index)

        id.setText(usuario?.id, TextView.BufferType.EDITABLE)
        nombre.setText(usuario?.nombre, TextView.BufferType.EDITABLE)
        apellido.setText(usuario?.apellido, TextView.BufferType.EDITABLE)
        cargo.setText(usuario?.cargo, TextView.BufferType.EDITABLE)
        correo.setText(usuario?.correo, TextView.BufferType.EDITABLE)
        password.setText(usuario?.password, TextView.BufferType.EDITABLE)

        btnActualizar.setOnClickListener{
            crud?.updateUsuario(Usuario(id.text.toString(), nombre.text.toString(), apellido.text.toString(),cargo.text.toString(),correo.text.toString(), password.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))

        }

        btnEliminar.setOnClickListener{
            crud?.deleteUsuario(Usuario(id.text.toString(), nombre.text.toString(), apellido.text.toString(),cargo.text.toString(),correo.text.toString(), password.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))

        }
    }
}