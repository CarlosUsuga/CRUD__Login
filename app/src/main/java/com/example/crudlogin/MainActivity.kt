package com.example.crudlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    var lista: RecyclerView? = null
    var adaptador:AdaptadorCustom? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    var usuarios:ArrayList<Usuario>? = null
    var crud:UsuarioCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        lista = findViewById(R.id.lista)

        lista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        lista?.layoutManager = layoutManager

        fabAdd.setOnClickListener{
            startActivity(Intent(this, NuevoUsuarioActivity::class.java))
        }

        crud = UsuarioCRUD(this)

        //organizar conexion con la funcion getUsuarios
        usuarios = crud?.getUsuarios()


        adaptador = AdaptadorCustom( usuarios!!, object : ClickListener{
            override fun onClick(vista: View, index: Int){
                //click
                val intent = Intent(applicationContext, DetalleUsuarioActivity::class.java)
                intent.putExtra("ID", usuarios!!.get(index).id)
                startActivity(intent)
            }
        }, object: LongClickListener{
            override fun longClick(vista: View, index: Int) {
                //Log.d("LONG","Prueba")
            }
        })

        lista?.adapter = adaptador
    }
}