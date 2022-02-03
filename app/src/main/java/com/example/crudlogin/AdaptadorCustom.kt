package com.example.crudlogin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCustom(items: ArrayList<Usuario>, var listener: ClickListener, var longClickListener: LongClickListener): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {

    var items:ArrayList<Usuario>? = null
    var multiSeleccion = false

    var itemSelecionados:ArrayList<Int>? = null
    var viewHolder:ViewHolder? = null

    init {
        this.items = items
        itemSelecionados = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.template_usuario, parent,false)
        val viewHolder = ViewHolder(vista, listener, longClickListener)

        return viewHolder!!
    }

    override fun getItemCount():Int{
        return items?.count()!!
    }

    fun iniciarActionMode(){
        multiSeleccion = true
    }

    fun desctruirActionMode(){
        multiSeleccion = false
        itemSelecionados?.clear()
        notifyDataSetChanged()
    }

    fun terminarActionMode(){
        //eliminar elementos seleccionados
        for (item in itemSelecionados!!){
            itemSelecionados?.remove(item)
        }
        multiSeleccion = false
        notifyDataSetChanged()
    }

    fun seleccionarItem(index:Int){
        if (multiSeleccion){
            if (itemSelecionados?.contains(index)!!){
                itemSelecionados?.remove(index)
            }else{
                itemSelecionados?.add(index)
            }
            notifyDataSetChanged()
        }
    }



    fun obtenerNumeroElementosSeleccionados():Int{
        return itemSelecionados?.count()!!
    }

    fun eliminarSeleccionado(){
        if(itemSelecionados?.count()!! > 0){
            var  itemsEliminados = ArrayList<Usuario>()

            for (index in itemSelecionados!!){
                itemsEliminados.add(items?.get(index)!!)
            }

            items?.removeAll(itemsEliminados)
            itemSelecionados?.clear()
        }
    }




    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //cambiar holder
        val item = items?.get(position)

        holder.id?.text = item?.id
        holder.nombre?.text = item?.nombre
        holder.apellido?.text = item?.apellido
        holder.cargo?.text = item?.cargo
        holder.correo?.text = item?.correo
        holder.password?.text = item?.password

        if(itemSelecionados?.contains(position)!!){
            //holder.vista.setBackgroundColor(Color.LTGRAY)
        }else{
            //holder.vista.setBackgroundColor(Color.WHITE)
        }
    }



    class ViewHolder(vista: View, listener: ClickListener, longClickListener: LongClickListener): RecyclerView.ViewHolder(vista), View.OnClickListener, View.OnLongClickListener {
        var vistas: View = vista

        var id: TextView? = null
        var nombre: TextView? = null
        var apellido: TextView? = null
        var cargo: TextView? = null
        var correo: TextView? = null
        var password: TextView? = null

        var listener:ClickListener? = null
        var longListener:LongClickListener? = null

        init {
            id = vista.findViewById(R.id.tvId)
            nombre = vista.findViewById(R.id.tvNombre)
            apellido = vista.findViewById(R.id.tvApellido)
            cargo = vista.findViewById(R.id.tvCargo)
            correo = vista.findViewById(R.id.tvCorreo)
            password = vista.findViewById(R.id.tvPassword)

            this.listener = listener
            this.longListener = longClickListener

            vista.setOnClickListener(this)
            vista.setOnLongClickListener(this)
        }

        override fun onClick(v: View?){
            this.listener?.onClick(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean{
            this.longListener?.longClick(v!!, adapterPosition)
            return true
        }
    }
}
