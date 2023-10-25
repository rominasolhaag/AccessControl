package com.example.accesscontrol_1.CRUD.modelos;

public class Usuarios {
    private int nro_dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private int telefono;
    private int telefono_emergencia;
    private int area_laboral;
    private int id_genero;
    private int id_rol;
    private int id_doc;

    //constructor por defecto
    public Usuarios (){}

    //constructores con argunemtos
        public Usuarios(int nro_dni, String nombre, String apellido, String direccion, String email,
                        int telefono, int telefono_emergencia, int area_laboral, int id_genero, int id_rol, int id_doc) {
            this.nro_dni = nro_dni;
            this.nombre = nombre;
            this.apellido = apellido;
            this.direccion = direccion;
            this.email = email;
            this.telefono = telefono;
            this.telefono_emergencia = telefono_emergencia;
            this.area_laboral = area_laboral;
            this.id_genero = id_genero;
            this.id_rol = id_rol;
            this.id_doc = id_doc;
    }

    public int getNro_dni() {
        return nro_dni;
    }

    public void setNro_dni(int nro_dni) {
        this.nro_dni = nro_dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getTelefono_emergencia() {
        return telefono_emergencia;
    }

    public void setTelefono_emergencia(int telefono_emergencia) {
        this.telefono_emergencia = telefono_emergencia;
    }

    public int getArea_laboral() {
        return area_laboral;
    }

    public void setArea_laboral(int area_laboral) {
        this.area_laboral = area_laboral;
    }

    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public int getId_doc() {
        return id_doc;
    }

    public void setId_doc(int id_doc) {
        this.id_doc = id_doc;
    }
    //metodo para mostrar el contenido

    @Override
    public String toString() {
        return "Usuarios{" +
                "nro_dni=" + nro_dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                ", telefono_emergencia=" + telefono_emergencia +
                ", area_laboral=" + area_laboral +
                ", id_genero=" + id_genero +
                ", id_rol=" + id_rol +
                ", id_doc=" + id_doc +
                '}';
    }
}


