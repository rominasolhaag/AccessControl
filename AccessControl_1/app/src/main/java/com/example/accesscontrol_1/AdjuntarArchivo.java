import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdjuntarArchivoActivity extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjuntar_archivo);

        Button attachButton = findViewById(R.id.attachButton);
        attachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre el selector de archivos.
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*"); // Todos los tipos de archivos.
                startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK) {
            // Se seleccionó un archivo.
            Uri selectedFileUri = data.getData();
            // Puedes trabajar con la URI del archivo seleccionado.
        }
    }
}