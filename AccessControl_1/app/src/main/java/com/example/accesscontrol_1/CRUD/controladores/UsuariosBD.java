package com.example.accesscontrol_1.CRUD.controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.accesscontrol_1.CRUD.modelos.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class UsuariosBD extends SQLiteOpenHelper implements IUsuariosBD {
    Context context;
    private List<Usuarios> usuariosList=new ArrayList<>();
    public UsuariosBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super (context, name, factory, version);
        this.context=context; // elementos que necesita el cosntrucctor de sql al inicio
    }
    @Override //primera version de la base de datos, se ejecuta una sola vez
    public void onCreate( SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE usuarios (" +
                "nro_dni INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "apellido TEXT," +
                "direccion TEXT," +
                "email TEXT," +
                "telefono INTERGER," +
                "telefono_emergencia INTERGER," +
                "area_laboral INTERGER," +//FORENG KEY
                "id_genero INTERGER," +
                "id_rol INTERGER," +
                "id_doc INTERGER," +
                "FOREIGN KEY (area_laboral) REFERENCES Area(area_laboral)," +
                "FOREIGN KEY (id_genero) REFERENCES Genero(id_genero)," +
                "FOREIGN KEY (id_rol) REFERENCES Rol(id_rol)," +
                "FOREIGN KEY (tipo_dni) REFERENCES Tipo_doc(id_doc))";
        sqLiteDatabase.execSQL( sql );
        String insert= "INSERT INTO usuarios VALUES (null, " +
                "'Jose'," +
                "'Perez'," +
                "'Corrientes 1500',"+
                "'joseperez@gmail.com', 151234567 , 159876543)";

        sqLiteDatabase.execSQL(insert);
    }//para cada tabla que tengamos, hacemos esto

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    @Override //traiga un elemento desde el id, consultar
    public Usuarios elemento(int nro_dni){
        SQLiteDatabase database= getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM usuarios WHERE nro_dni='"+nro_dni+"", null);  //recibe la consulta
        try{
            if (cursor.moveToNext())
                return extraerUsuario(cursor);
            else
                return null;
        }catch (Exception e){Log.d ("TAG","error elemento nro_dni UsuarioBD" + e.getMessage());

            throw e;
        }finally {
            if (cursor !=null) cursor.close();
        }  // dice si hubiera error
    }
    private Usuarios extraerUsuario(Cursor cursor){
        Usuarios usuarios = new Usuarios();
        usuarios.setNro_dni(cursor.getInt(0));
        usuarios.setNombre(cursor.getString(1));
        usuarios.setApellido(cursor.getString(2));
        usuarios.setDireccion(cursor.getString(3));
        usuarios.setEmail(cursor.getString(4));
        usuarios.setTelefono(cursor.getInt(5));
        usuarios.setTelefono_emergencia(cursor.getInt(6));
        usuarios.setArea_laboral(cursor.getInt(7));
        usuarios.setId_genero(cursor.getInt(8));
        usuarios.setId_rol(cursor.getInt(9));
        usuarios.setId_doc(cursor.getInt(10));
        return usuarios;//devuelve objeto usuario
    }
    @Override
    public Usuarios elemento(String nombre){
        SQLiteDatabase database= getReadableDatabase();
        String sql= "SELECT * FROM Usuarios WHERE nombre='"+ nombre +"'";
        Cursor cursor= database.rawQuery(sql, null);   //recibe la consulta
        try{
            if (cursor.moveToNext())
                return extraerUsuario(cursor);
            else
                return null;
        }catch (Exception e){Log.d ("TAG","error elemento nombre UsuarioBD" + e.getMessage());
            throw e;
        }finally {
            if (cursor !=null) cursor.close();
        }
    }


    @Override
    public List<Usuarios> lista(){
        SQLiteDatabase database = getReadableDatabase();
        String sql= "SELECT * FROM Usuario ORDER BY nombre ASC";
        Cursor cursor=database.rawQuery(sql, null);
        List<Usuarios> usuarioslist = null;
        if ( cursor.moveToFirst()){
            do{
                usuarioslist.add(
                        new Usuarios(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getInt(5),
                                cursor.getInt(6),
                                cursor.getInt(7),
                                cursor.getInt(8),
                                cursor.getInt(9),
                                cursor.getInt(10)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return usuarioslist;
    }

    @Override
    public void agregar(Usuarios usuarios){ //no ponemos nro_dni porque siendo autoincremental, solo va agregando
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("nombre",usuarios.getNombre());
        values.put("apellido",usuarios.getApellido());
        values.put("direccion",usuarios.getEmail());
        values.put("email",usuarios.getEmail());
        values.put("telefono",usuarios.getTelefono());
        values.put("telefono_emergencia",usuarios.getTelefono_emergencia());
        values.put("area_laboral",usuarios.getArea_laboral());
        values.put("id_genero",usuarios.getId_genero());
        values.put("id_rol",usuarios.getId_rol());
        values.put("id_doc",usuarios.getId_doc());
        database.insert("Usuario", null, values); //se agregan sin ninguna condicion

    }

    @Override
    public void actualizar(int nro_dni, Usuarios usuarios){
        SQLiteDatabase database=this.getWritableDatabase();
        String[]parametros={String.valueOf(nro_dni)};
        ContentValues values=new ContentValues();
        values.put("nombre",usuarios.getNombre());
        values.put("apellido",usuarios.getApellido());
        values.put("direccion",usuarios.getEmail());
        values.put("email",usuarios.getEmail());
        values.put("telefono",usuarios.getTelefono());
        values.put("telefono_emergencia",usuarios.getTelefono_emergencia());
        values.put("area_laboral",usuarios.getArea_laboral());
        values.put("id_genero",usuarios.getId_genero());
        values.put("id_rol",usuarios.getId_rol());
        values.put("id_doc",usuarios.getId_doc());
        database.update("Usuario", values, "nro_dn=?", parametros); //ahi si se pudo una condicion, que sea igual a nro_dni

    }


    @Override
    public void borrar(int nro_dni) {
        SQLiteDatabase database=this.getWritableDatabase();
        String[]parametros={String.valueOf(nro_dni)};

        database.delete("Usuarios","nro_dni=?", parametros);
    }// aca terminan las operaciones con la base de datos

    @Override
    public void borrar(int nro_dni, Usuarios usuarios) {
        
    }

}
