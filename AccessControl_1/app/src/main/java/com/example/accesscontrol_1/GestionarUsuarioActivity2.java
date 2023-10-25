package com.example.accesscontrol_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.accesscontrol_1.CRUD.controladores.UsuariosBD;

public class GestionarUsuarioActivity2 extends AppCompatActivity {
    Context context;
    EditText txtnombre, txtapellido, txtdireccion, txtemail, txttelefono, txttelefono_emergencia;
    int nro_dni;
    UsuariosBD usuariosBD;
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

        Intent i=getIntent();
        Bundle bolsa=i.getExtras();
        nro_dni= bolsa.getInt( "nro_dni");
        //se llamo el activity desde la opcion de buscar
        if (nro_dni !=0){
            txtnombre.setText(bolsa.getString("nombre"));
            txtapellido.setText(bolsa.getString("apellido"));
            txtdireccion.setText(bolsa.getString("direccion"));
            txtemail.setText(bolsa.getString("email"));
            txttelefono.setText(bolsa.getInt("telefono"));
            txttelefono_emergencia.setText(bolsa.getInt("telefono_emergencia"));
        }
    }
}