package com.example.accesscontrol;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;

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

        // Configurar OnClickListener para el botón "Adjuntar archivos"
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonSi.isChecked()) {
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

        // Configurar OnClickListener para el botón "Enviar"
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonSi.isChecked()) {
                    // Procesar el caso de "Aviso de ausentismo"
                    // Si es necesario, acceder a los archivos seleccionados previamente.
                } else {
                    // Procesar el caso de "Aviso de tardanza"
                    if (radioButtonSi1.isChecked()) {
                        String motivoTardanza = commentEditText.getText().toString();
                        // Procesar el motivo de la tardanza.
                    } else {
                        // Usuario seleccionó "No" en "Aviso de tardanza".
                        // Procesar como llegada a tiempo.
                    }
                }
            }
        });
    }

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
                // El usuario concedió el permiso, puedes abrir el selector de archivos.
                openFilePicker();
            } else {
                // El usuario denegó el permiso, debes manejar esto de manera adecuada.
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
                // Puedes agregar acciones adicionales si es necesario.
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}