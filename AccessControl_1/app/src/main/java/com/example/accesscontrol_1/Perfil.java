package com.example.accesscontrol_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class Perfil extends Activity {

    private TextView switchTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        Switch mySwitch = findViewById(R.id.mySwitch); // ID del Switch
        switchTextView = findViewById(R.id.switchTextView); // ID del TextView

        mySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                switchTextView.setText(getString(R.string.yes));
            } else {
                switchTextView.setText(getString(R.string.no));
            }
        });

        // Botón "Edit" (profileButton)
        Button editButton = findViewById(R.id.profileButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad "datos_personales"
                Intent intent = new Intent(Perfil.this, DatosPersonales.class);
                startActivity(intent);
            }
        });

        // Botón "Exit" (belowBoxButton)
        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad "ingreso"
                Intent intent = new Intent(Perfil.this, Ingreso.class);
                startActivity(intent);
            }
        });

        // Barra de navegación
        ImageView elemento1 = findViewById(R.id.navhome);
        ImageView elemento2 = findViewById(R.id.navqr);
        ImageView elemento3 = findViewById(R.id.navprofile);

        elemento1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad "inicio"
                Intent intent = new Intent(Perfil.this, Inicio.class);
                startActivity(intent);
            }
        });

        elemento2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad "registro"
                Intent intent = new Intent(Perfil.this, Registro.class);
                startActivity(intent);
            }
        });

        // No es necesario implementar OnClickListener para el elemento 3 (Perfil), ya que ya estamos en la pestaña "Perfil".
    }
}
