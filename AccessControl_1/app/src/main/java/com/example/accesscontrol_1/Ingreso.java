package com.example.accesscontrol_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Ingreso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingreso);
    }

    public void ingresar(View v){
        Intent intent = new Intent(Ingreso.this, Inicio.class);
        startActivity(intent);
    }
}