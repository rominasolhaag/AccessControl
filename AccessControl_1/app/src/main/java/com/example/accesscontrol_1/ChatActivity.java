import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {
    private EditText editText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editText = findViewById(R.id.editText);
        sendButton = findViewById(R.id.sendButton);

        // Obtiene una referencia a la instancia de Firebase Realtime Database.
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Define una referencia al nodo "mensajes" en la base de datos.
        DatabaseReference mensajesRef = database.getReference("mensajes");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = editText.getText().toString();

                // Genera un nuevo identificador único para el mensaje.
                String mensajeId = mensajesRef.push().getKey();

                // Guarda el mensaje en la base de datos bajo el identificador único.
                mensajesRef.child(mensajeId).setValue(mensaje);

                // Limpia el EditText después de enviar el mensaje.
                editText.setText("");
            }
        });
    }
}