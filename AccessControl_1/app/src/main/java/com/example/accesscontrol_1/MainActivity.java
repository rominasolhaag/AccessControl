package com.example.accesscontrol_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    Button btnListar, btnregistrar, btnBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Button btnRegistrar = findViewById(R.id.btnregistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Acciones a realizar cuando se hace clic en el botón "btnregistrar"
            }
        });

    }
    private void init(){
        context= getApplicationContext();
        btnregistrar= findViewById(R.id.btnregistrar);
        btnBuscar= findViewById(R.id.btnbuscar);
        btnListar= findViewById(R.id.btnlistar);
    }



    @Override
    public void onClick(View view) {

    }
}










//Abrimos la base de datos ‘DBUsuarios’ en modo escritura        
        //UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);
       // SQLiteDatabase db = usdbh.getWritableDatabase();
//Si hemos abierto correctamente la base de datos
        //if(db != null)

//Insertamos 5 usuarios de ejemplo
           // for(int i=1; i<=5; i++)

//Generamos los datos
                //int codigo = i;
               // String nombre = "Usuario" + i;
//Insertamos los datos en la tabla Usuarios
               // db.execSQL("INSERT INTO Usuarios (codigo, nombre) " +
 //"VALUES (" + codigo + ", '" + nombre +"')");

//Cerramos la base de datos
           // db.close();
