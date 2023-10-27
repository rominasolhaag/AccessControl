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
    Button btnListar, btnRegistrar, btnBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    private void init(){
        context= getApplicationContext();
        btnRegistrar= findViewById(R.id.btnRegistrar);
        btnBuscar= findViewById(R.id.btnBuscar);
        btnListar= findViewById(R.id.btnListar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Acciones a realizar cuando se hace clic en el botón "Registrar"
                Intent i = new Intent(context, GestionarUsuarioActivity2.class); // Reemplaza con la actividad a la que deseas ir
                startActivity(i);
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Acciones a realizar cuando se hace clic en el botón "Buscar"
                    Intent i = new Intent(context, BuscarUsuarioActivity.class); // Reemplaza con la actividad a la que deseas ir
                    startActivity(i);
                }
            });

        btnListar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Acciones a realizar cuando se hace clic en el botón "Listar"
                    Intent i = new Intent(context, ListadoUsuariosActivity2.class); // Reemplaza con la actividad a la que deseas ir
                    startActivity(i);
                }
            });

    }


        @Override
        public void onClick(View view) {
            int viewId = view.getId();

            if (viewId == R.id.btnRegistrar) {
                Toast.makeText(context, "Registrar", Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, GestionarUsuarioActivity2.class);
                Bundle bolsa= new Bundle();
                bolsa.putInt("Nro_dni",0);
                i.putExtras(bolsa);
                startActivity(i);
            } else if (viewId == R.id.btnListar) {
                Toast.makeText(context, "Listar", Toast.LENGTH_LONG).show();
                Intent i2 = new Intent(context, ListadoUsuariosActivity2.class);
                startActivity(i2);
            } else if (viewId == R.id.btnBuscar) {
                Toast.makeText(context, "Buscar", Toast.LENGTH_LONG).show();
                Intent i3 = new Intent(context, BuscarUsuarioActivity.class);
                startActivity(i3);
            }
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
