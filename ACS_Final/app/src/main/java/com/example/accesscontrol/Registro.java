package com.example.accesscontrol;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Registro extends Activity {
    private static final int CAMERA_PERMISSION_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        Button scanButton = findViewById(R.id.scanButton);
        scanButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(Registro.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                escanearCodigoQR();
            } else {
                ActivityCompat.requestPermissions(Registro.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            escanearCodigoQR();
        }
    }

    private void escanearCodigoQR() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.setBeepEnabled(true);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            String scannedText = result.getContents();
            if (scannedText != null) {
                // Escaneo exitoso, redirige a la vista "inicio.xml"
                Intent inicioIntent = new Intent(this, Inicio.class);
                startActivity(inicioIntent);
            } else {
                // Escaneo fallido, no hace nada (permanece en la vista actual)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

