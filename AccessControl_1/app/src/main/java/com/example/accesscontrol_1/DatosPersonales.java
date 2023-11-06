package com.example.accesscontrol_1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

public class DatosPersonales extends Activity {

    private TextView switchTextView;
    private TextView customView;
    private TextView customView2;
    private DatePickerDialog datePickerDialog;

    private boolean aceptaNotificaciones = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_personales);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Botón "back"
        Button backButton = findViewById(R.id.textButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerramos la actividad actual y regresamos a la anterior
                finish();
            }
        });

        // Barra de navegación
        ImageView elemento1 = findViewById(R.id.navhome);
        ImageView elemento2 = findViewById(R.id.navqr);
        ImageView elemento3 = findViewById(R.id.navprofile);

        elemento1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad "Inicio"
                Intent intent = new Intent(DatosPersonales.this, Inicio.class);
                startActivity(intent);
            }
        });

        elemento2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad "Registro"
                Intent intent = new Intent(DatosPersonales.this, Registro.class);
                startActivity(intent);
            }
        });

        elemento3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad "Perfil"
                Intent intent = new Intent(DatosPersonales.this, Perfil.class);
                startActivity(intent);
        }
    });

    switchTextView = findViewById(R.id.switchTextView);

    notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    // Establece el texto inicial
    if (aceptaNotificaciones) {
        switchTextView.setText("Si");
    } else {
        switchTextView.setText("No");
    }

    switchTextView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Cambia el estado y el texto
            aceptaNotificaciones = !aceptaNotificaciones;

            if (aceptaNotificaciones) {
                switchTextView.setText("Si");
                // Habilitar notificaciones: registrar al usuario para recibir notificaciones
                // Firebase Cloud Messaging (FCM) u otras bibliotecas de notificaciones
            } else {
                switchTextView.setText("No");
                // Deshabilita notificaciones: desregistrar al usuario de las notificaciones
            }
        }
    });

    Button buttonLicencia = findViewById(R.id.buttonLicencia);
    customView = findViewById(R.id.customView);
    customView2 = findViewById(R.id.customView2);

    buttonLicencia.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    String segundaFecha = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    String textoCompleto = customView.getText() + " al " + segundaFecha;
                    customView2.setText(textoCompleto);
                }
            };
            datePickerDialog = new DatePickerDialog(DatosPersonales.this, dateSetListener, 2023, 0, 1);
            datePickerDialog.show();
        }
    });
}

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                customView.setText(selectedDate);
            }
        };
        datePickerDialog = new DatePickerDialog(this, dateSetListener, 2023, 0, 1);
        datePickerDialog.show();
    }
}
