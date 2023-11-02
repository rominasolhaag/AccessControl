import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SelectorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        // Obtiene una referencia al Spinner desde el archivo de diseño.
        Spinner spinner = findViewById(R.id.spinner);

        // Crea un arreglo de opciones que deseas mostrar en el Spinner.
        String[] opciones = {"Opción 1", "Opción 2", "Opción 3", "Opción 4"};

        // Crea un ArrayAdapter para el Spinner y asigna las opciones.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);

        // Configura el diseño del Spinner (puede ser simple o desplegado).
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asigna el adaptador al Spinner.
        spinner.setAdapter(adapter);

        // Agrega un listener para manejar la selección del usuario.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Maneja la selección del usuario aquí, por ejemplo:
                String opcionSeleccionada = opciones[position];
                // Realiza acciones basadas en la opción seleccionada.
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Si no se selecciona nada.
            }
        });
    }
}
