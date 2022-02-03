package com.example.crudlogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    var etUsuario: EditText? = null
    var etPassword: EditText? = null
    var btn_InicioSesion: Button? = null
    var btn_registrar: Button? = null

    var crud: UsuarioCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsuario = findViewById<EditText>(R.id.etUsuario)
        etPassword = findViewById<EditText>(R.id.etPassword)
        btn_InicioSesion = findViewById<Button>(R.id.btn_InicioSesion)
        btn_registrar = findViewById<Button>(R.id.btn_registrar)

        crud = UsuarioCRUD(this)

    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_InicioSesion -> {
                val u: String = etUsuario?.getText().toString()
                val p: String = etPassword?.getText().toString()
                if (u == "" && p == "") {
                    Toast.makeText(this, "Campos vacios", Toast.LENGTH_LONG).show()
                } else if (u != null && p != null) {
                    val ux: Usuario = crud!!.getUsuario(u)
                    Toast.makeText(this, "Datos Correctos", Toast.LENGTH_LONG).show()
                    val f = Intent(this, MainActivity::class.java)
                    f.putExtra("ID", ux.id)
                    startActivity(f)
                    finish()
                } else {
                    Toast.makeText(this, "Usuario Y/o constraseña Incorrecta", Toast.LENGTH_LONG)
                        .show()
                }
            }
            R.id.btn_registrar -> {
                val i = Intent(this, NuevoUsuarioActivity::class.java)
                startActivity(i)
            }
        }
    }

}