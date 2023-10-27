package com.example.accesscontrol_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.accesscontrol_1.CRUD.controladores.UsuariosBD;
import com.example.accesscontrol_1.CRUD.modelos.Usuarios;

public class GestionarUsuarioActivity2 extends AppCompatActivity implements View.OnClickListener{
    Context context;
    EditText txtnombre, txtapellido, txtdireccion, txtemail, txttelefono, txttelefono_emergencia;
    int nro_dni;
    UsuariosBD usuariosBD;
    Button btnguardar, btnactualizar,btnborrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_usuario2);
        init();
    }
    private void init(){
        context= getApplicationContext();
        txtnombre=findViewById(R.id.ges_txtnombre);
        txtapellido=findViewById(R.id.ges_txtapellido);
        txtdireccion=findViewById(R.id.ges_txtdireccion);
        txtemail=findViewById(R.id.ges_txtemail);
        txttelefono=findViewById(R.id.ges_txttelefono);
        txttelefono_emergencia=findViewById(R.id.ges_txttelefono_emergencia);

        btnactualizar=findViewById(R.id.ges_btnactualizar);
        btnborrar=findViewById(R.id.ges_btnborrar);
        btnguardar=findViewById(R.id.ges_btnguardar);

        Intent i=getIntent();
        Bundle bolsa=i.getExtras();
        nro_dni= bolsa.getInt( "nro_dni");
        //se llamo el activity desde la opcion de buscar
        if (nro_dni !=0){
            txtnombre.setText(bolsa.getString("nombre"));
            txtapellido.setText(bolsa.getString("apellido"));
            txtdireccion.setText(bolsa.getString("direccion"));
            txtemail.setText(bolsa.getString("email"));
            txttelefono.setText(bolsa.getInt("telefono")+"");
            txttelefono_emergencia.setText(bolsa.getInt("telefono_emergencia")+"");
            btnguardar.setEnabled(false);
        }else {
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
        }
    }
    private void limpiarcampos(){
        nro_dni=0;
        txtnombre.setText("");
        txtapellido.setText("");
        txtdireccion.setText("");
        txtemail.setText("");
        txttelefono.setText("");
        txttelefono_emergencia.setText("");
    }
    private Usuarios llenardatosusuarios(){
        Usuarios usuarios= new Usuarios();
        String n =txtnombre.getText().toString();
        String a =txtapellido.getText().toString();
        String d =txtdireccion.getText().toString();
        String e =txtemail.getText().toString();
        String t =txttelefono.getText().toString();
        String te =txttelefono_emergencia.getText().toString();
        usuarios.setNro_dni(nro_dni);
        usuarios.setNombre(n);
        usuarios.setApellido(a);
        usuarios.setDireccion(d);
        usuarios.setEmail(e);
        usuarios.setTelefono(Integer.parseInt(t));
        usuarios.setTelefono_emergencia(Integer.parseInt(te));

        return usuarios;
    }

    private void guardar(){

        usuariosBD=new UsuariosBD(context, "UsuariosBD.db", null, 1);
        Usuarios usuarios = llenardatosusuarios();
        if (nro_dni == 0){
            usuariosBD.agregar(usuarios);
            Toast.makeText(context, "Guardado Nuevo ok", Toast.LENGTH_SHORT).show();
            //si existe, estosnce sera una modificacion
        }else {
            usuariosBD.actualizar(nro_dni,usuarios);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            Toast.makeText(context, "Actualizaco existente ok", Toast.LENGTH_LONG).show();
        }
    }

    private void borrar (){
        usuariosBD=new UsuariosBD(context, "UsuariosBD.db", null, 1);
        Usuarios usuarios = llenardatosusuarios();
        if (nro_dni == 0){
            Toast.makeText(context, "No es posible borrar", Toast.LENGTH_SHORT).show();
            //si existe, estosnce sera una modificacion
        }else {
            usuariosBD.borrar(nro_dni);
            limpiarcampos();
            btnguardar.setEnabled(true);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            //se desactivan porque la accion no los requiere
            Toast.makeText(context, "Se borro el registro", Toast.LENGTH_LONG).show();

    }
}


        @Override
        public void onClick(View view) {
            int viewId = view.getId();

            if (viewId == R.id.ges_btnguardar || viewId == R.id.ges_btnactualizar) {
                guardar();
            } else if (viewId == R.id.ges_btnborrar) {
                borrar();
            } else {
                // Manejar otros casos si es necesario
            }
        }

}