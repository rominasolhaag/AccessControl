
package com.example.accesscontrol;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.squareup.timessquare.CalendarPickerView;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

import com.squareup.timessquare.CalendarPickerView.SelectionMode;


public class Licencias extends Activity {
    private CalendarPickerView calendarView;
    private Button confirmButton;
    private TextView selectedRangeText;
    private Date selectedStartDate;
    private Date selectedEndDate;
    private List<Pair<Date, Date>> dateRanges; // Lista para almacenar los rangos de fechas desde la base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.licencias);
        // Definir valores de prueba para fechaInicial y fechaFinal
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicial = null;
        Date fechaFinal = null;

        try {
            fechaInicial = dateFormat.parse("2023-01-01");
            fechaFinal = dateFormat.parse("2023-12-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendarView = findViewById(R.id.calendar_view);
        confirmButton = findViewById(R.id.confirm_button);
        selectedRangeText = findViewById(R.id.selected_range_text);

        // Obtener los rangos de fechas desde la base de datos
        dateRanges = getDateRangesFromDatabase();

        List<Date> highlightedDates = new ArrayList<>();
        for (Pair<Date, Date> dateRange : dateRanges) {
            highlightedDates.add(dateRange.first);
            highlightedDates.add(dateRange.second);
        }

        // Inicializar el CalendarPickerView con los rangos de fechas
        calendarView.init(fechaInicial, fechaFinal)
                .inMode(SelectionMode.RANGE)
                .withSelectedDates(new ArrayList<Date>())
                .withHighlightedDates(highlightedDates);

        calendarView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                if (selectedStartDate == null) {
                    selectedStartDate = date;
                    selectedEndDate = null;
                } else if (selectedEndDate == null) {
                    selectedEndDate = date;
                } else {
                    selectedStartDate = date;
                    selectedEndDate = null;
                }
                updateSelectedRangeText();
            }

            @Override
            public void onDateUnselected(Date date) {
                selectedStartDate = null;
                selectedEndDate = null;
                updateSelectedRangeText();
            }
        });

        confirmButton.setOnClickListener(v -> {
            // Aquí puedes guardar el rango seleccionado en la base de datos
            if (selectedStartDate != null && selectedEndDate != null) {
                dateRanges.add(new Pair<>(selectedStartDate, selectedEndDate));
                // Luego, guarda 'dateRanges' en la base de datos o realiza las operaciones necesarias.
            }
        });


        ImageView elemento1 = findViewById(R.id.navhome);
        ImageView elemento2 = findViewById(R.id.navqr);
        ImageView elemento3 = findViewById(R.id.navprofile);

        elemento1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Licencias.this, Inicio.class);
                startActivity(intent);
            }
        });

        elemento2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Licencias.this, Registro.class);
                startActivity(intent);
            }
        });

        elemento3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Licencias.this, Perfil.class);
                startActivity(intent);
            }
        });
    }

    private List<Pair<Date, Date>> getDateRangesFromDatabase() {
        // Implementa aquí la lógica para obtener los rangos de fechas de la base de datos
        List<Pair<Date, Date>> dateRanges = new ArrayList<>();
        // Por ejemplo:
        dateRanges.add(new Pair<>(new Date(), new Date()));
        dateRanges.add(new Pair<>(new Date(), new Date()));
        return dateRanges;
    }

    private void updateSelectedRangeText() {
        if (selectedStartDate != null) {
            String rangeText = "Rango seleccionado: " + selectedStartDate;
            if (selectedEndDate != null) {
                rangeText += " - " + selectedEndDate;
            }
            selectedRangeText.setText(rangeText);
        } else {
            selectedRangeText.setText("");
        }
    }

    private List<String> motivoOpciones = Arrays.asList("Vacaciones", "Enfermedad", "Asuntos personales", "Otros");

}