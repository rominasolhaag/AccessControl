package com.example.accesscontrol_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewGroupKt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.accesscontrol_1.CRUD.controladores.UsuariosBD;
import com.example.accesscontrol_1.CRUD.modelos.Usuarios;

public class BuscarUsuarioActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText txtnombre;
    Button btnBuscar;
    UsuariosBD usuariosBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_usuario);
        init();

    }

    private void init(){
        context= getApplicationContext();
        txtnombre=findViewById(R.id.bus_txtnombre);
        btnBuscar=findViewById(R.id.bus_btnBuscar);

    }

    @Override
    public void onClick(View view) {
    // cuando se hace click en el botton
        if(view.getId()== R.id.bus_btnBuscar){
            String nombre=txtnombre.getText().toString();
            Usuarios usuarios= buscarusuarios(nombre);
            //que si trae un nombre lo muestre
            if (usuarios !=null){
                Bundle bolsa= new Bundle();
                bolsa.putInt("nro_dni", usuarios.getNro_dni());
                bolsa.putString("nombre", usuarios.getNombre());
                bolsa.putString("apellido", usuarios.getApellido());
                bolsa.putString("direccion", usuarios.getDireccion());
                bolsa.putString("email", usuarios.getEmail());
                bolsa.putInt("telefono", usuarios.getTelefono());
                bolsa.putInt("telefono_emergencia", usuarios.getTelefono_emergencia());
                bolsa.putInt("area_laboral", usuarios.getArea_laboral());
                bolsa.putInt("id_genero", usuarios.getId_genero());
                bolsa.putInt("id_rol", usuarios.getId_rol());
                bolsa.putInt("id_doc", usuarios.getId_doc());

                Intent i =new Intent(context, GestionarUsuarioActivity2.class);
                //manda el contenido de la informacion
                i.putExtras( bolsa);
                startActivity(i);
            }else{
                Toast.makeText(context, "No existe un empleado con ese nombre",
                        Toast.LENGTH_LONG).show();

            }

        }}


    private Usuarios buscarusuarios(String nombre) {
        usuariosBD=new UsuariosBD( context, "UsuariosBD.bd", null, 1);
        Usuarios usuarios=usuariosBD.elemento(nombre);

        return usuariosBD.elemento(nombre);
    }
}