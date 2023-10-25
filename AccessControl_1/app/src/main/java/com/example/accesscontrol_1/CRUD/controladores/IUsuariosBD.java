package com.example.accesscontrol_1.CRUD.controladores;

import com.example.accesscontrol_1.CRUD.modelos.Usuario;
import com.example.accesscontrol_1.CRUD.modelos.Usuarios;
import java.util.List;

public interface IUsuariosBD {
    Usuarios elemento (int nro_dni); //devuleve dato poniendo nro dni
    Usuarios elemento (String nombre);
    List<Usuarios> lista(); //trae una lista con todos los elementos registrados

    void agregar (Usuarios usuarios);
    void actualizar (int nro_dni, Usuarios usuarios);
    void borrar (int nro_dni);

    void borrar(int nro_dni, Usuarios usuarios)// aca terminan las operaciones con la base de datos
    ;
}
