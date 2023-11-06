package com.example.accesscontrol_1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class Documentacion extends Activity {

    private RadioButton radioButtonSi;
    private RadioButton radioButtonNo;
    private RadioButton radioButtonSi1;
    private RadioButton radioButtonNo1;
    private Button button;
    private Button sendButton;
    private EditText commentEditText;

    private static final int REQUEST_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documentacion);

        radioButtonSi = findViewById(R.id.radioButtonSi);
        radioButtonNo = findViewById(R.id.radioButtonNo);
        radioButtonSi1 = findViewById(R.id.radioButtonSi1);
        radioButtonNo1 = findViewById(R.id.radioButtonNo1);
        button = findViewById(R.id.button);
        sendButton = findViewById(R.id.sendButton);
        commentEditText = findViewById(R.id.commentEditText);

        // Botón "Adjuntar archivos"
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonNo.isChecked()) {
                    if (checkStoragePermission()) {
                        openFilePicker();
                    } else {
                        requestStoragePermission();
                    }
                } else {
                    // Mostrar un mensaje de error
                    showErrorMessage("Debe figurar como 'ausente' para subir un archivo");
                }
            }
        });

        //Botón "Enviar"
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonSi.isChecked()) {
                    // Procesar el caso de "Aviso de ausentismo"
                    // Si es necesario, acceder a los archivos seleccionados previamente.
                } else if (radioButtonSi1.isChecked()) {
                    // Procesar el caso de "Aviso de tardanza"
                    String motivoTardanza = commentEditText.getText().toString();
                    // Procesar el motivo de la tardanza.
                } else if (radioButtonNo1.isChecked()) {
                    // Procesar como llegada a tiempo.
                }
            }
        });

        // Inicialmente, deshabilitar "Adjuntar archivos" y "Enviar"
        button.setEnabled(false);
        sendButton.setEnabled(false);

        // "Si" en "Aviso de ausentismo"
        radioButtonSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setEnabled(true); // Habilitar "Adjuntar archivos"
                radioButtonSi1.setChecked(false); // Desactivar "Si" en "Aviso de tardanza"
                radioButtonNo1.setChecked(false); // Desactivar "No" en "Aviso de tardanza"
                sendButton.setEnabled(true); // Habilitar "Enviar"
            }
        });

        // "No" en "Aviso de ausentismo"
        radioButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setEnabled(false); // Deshabilitar "Adjuntar archivos"
                radioButtonSi1.setChecked(false); // Desactivar "Si" en "Aviso de tardanza"
                radioButtonNo1.setChecked(false); // Desactivar "No" en "Aviso de tardanza"
                commentEditText.setEnabled(false); // Deshabilitar la edición del motivo de la tardanza
                sendButton.setEnabled(false); // Deshabilitar "Enviar"
            }
        });

        // "Si" en "Aviso de tardanza"
        radioButtonSi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setEnabled(false); // Deshabilitar "Adjuntar archivos"
                commentEditText.setEnabled(true); // Habilitar la edición del motivo de la tardanza
                sendButton.setEnabled(true); // Habilitar "Enviar"
            }
        });

        // "No" en "Aviso de tardanza"
        radioButtonNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setEnabled(false); // Deshabilitar "Adjuntar archivos"
                commentEditText.setEnabled(false); // Deshabilitar la edición del motivo de la tardanza
                sendButton.setEnabled(true); // Habilitar "Enviar"
            }
        });

        // Barra de navegación
        ImageView elemento1 = findViewById(R.id.navhome);
        ImageView elemento2 = findViewById(R.id.navqr);
        ImageView elemento3 = findViewById(R.id.navprofile);

        elemento1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciamos la actividad "Inicio"
                Intent intent = new Intent(Documentacion.this, Inicio.class);
                startActivity(intent);
            }
        });

        elemento2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad "Registro"
                Intent intent = new Intent(Documentacion.this, Registro.class);
                startActivity(intent);
            }
        });

        elemento3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad "Perfil"
                Intent intent = new Intent(Documentacion.this, Perfil.class);
                startActivity(intent);
            }
        });
    }

    // Métodos para verificar y solicitar permisos, así como para abrir el selector de archivos.

    // Método para verificar permisos
    private boolean checkStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    // Método para solicitar permisos
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
    }

    // Método para abrir el selector de archivos
    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, REQUEST_PERMISSION_CODE);
    }

    // Método para manejar la respuesta de solicitud de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // El usuario concedió el permiso
                openFilePicker();
            } else {
                // El usuario denegó el permiso
            }
        }
    }

    // Método para mostrar un mensaje de error
    private void showErrorMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}