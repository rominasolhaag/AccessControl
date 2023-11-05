package com.example.accesscontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Calendario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendario);

        ImageView elemento1 = findViewById(R.id.navhome);
        ImageView elemento2 = findViewById(R.id.navqr);
        ImageView elemento3 = findViewById(R.id.navprofile);

        elemento1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Calendario.this, Inicio.class);
                startActivity(intent);
            }
        });

        elemento2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Calendario.this, Registro.class);
                startActivity(intent);
            }
        });

        elemento3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Calendario.this, Perfil.class);
                startActivity(intent);
            }
        });
    }

    
}

