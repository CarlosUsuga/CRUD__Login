package com.example.crudlogin

class Usuario(id:String, nombre:String, apellido:String, cargo:String, correo:String, password:String) {

    var id:String? = null
    var nombre:String? = null
    var apellido:String? = null
    var cargo:String? = null
    var correo:String? = null
    var password:String? = null

    init {

        this.id = id
        this.nombre = nombre
        this.apellido = apellido
        this.cargo = cargo
        this.correo = correo
        this.password = password
    }


}