package com.example.accesscontrol_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.accesscontrol_1.CRUD.controladores.SelectListener;
import com.example.accesscontrol_1.CRUD.controladores.UsuariosBD;
import com.example.accesscontrol_1.CRUD.modelos.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class ListadoUsuariosActivity2 extends AppCompatActivity implements SelectListener {

    ListView listView;
    ArrayList<String> nombreusuarios;
    ArrayList<Integer> nro_dniusuarios;
    UsuariosBD usuariosBD;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_usuarios2);
        init();
    }

    private void init(){
        context= this.getApplicationContext();
        usuariosBD=new UsuariosBD(context, "UsuariosBD.bd", null, 1);
        listView=findViewById(R.id.listausuarios);
        llenarListView();

    }

    private void llenarListView() {
        nombreusuarios=new ArrayList<String>();
        nro_dniusuarios=new ArrayList<Integer>();
        List<Usuarios> listaUsuarios = usuariosBD.lista();
        //para recorrer los usuarios
        for(int i=0; i<listaUsuarios.size(); i++){
            Usuarios l= listaUsuarios.get(i);
            nombreusuarios.add(l.getNombre());
            nro_dniusuarios.add(l.getNro_dni());

        }
        ArrayAdapter<String> adapter=
                new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,
                        nombreusuarios);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Usuarios usuarios= usuariosBD.elemento(nro_dniusuarios.get(i));
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

                Intent intent =new Intent(context, GestionarUsuarioActivity2.class);
                //manda el contenido de la informacion
                intent.putExtras( bolsa);
                startActivity(intent);
            }
        });
    }

    @Override
    public void OnItemClick(String nombre) {
        
    }
}