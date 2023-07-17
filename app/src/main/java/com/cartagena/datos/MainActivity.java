package com.cartagena.datos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText etCorreo;
    private EditText etPassword;
    private Spinner spinnerLista;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCorreo = findViewById(R.id.etCorreo);
        etPassword = findViewById(R.id.etPassword);
        Button btnCuenta = findViewById(R.id.btnCuenta);
        Button btnIngresar = findViewById(R.id.btnIngresar);
        spinnerLista = findViewById(R.id.spinnerLista);

        // Opciones del spinner
        String[] opciones = {"Producto", "Persona", "Inventario"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLista.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();

        btnCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí se ejecuta el código cuando se hace clic en el botón "btnCuenta"
                // Agrega el código que desees para abrir la clase de Cuenta
                Intent intent = new Intent(MainActivity.this, Cuenta.class);
                startActivity(intent);
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí se ejecuta el código cuando se hace clic en el botón "btnIngresar"
                String correo = etCorreo.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Autenticar al usuario con Firebase
                mAuth.signInWithEmailAndPassword(correo, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Autenticación exitosa, proceder a abrir la actividad correspondiente
                                    String selectedOption = spinnerLista.getSelectedItem().toString();
                                    if (selectedOption.equals("Persona")) {
                                        Intent intent = new Intent(MainActivity.this, Persona.class);
                                        startActivity(intent);
                                    } else if (selectedOption.equals("Inventario")) {
                                        Intent intent = new Intent(MainActivity.this, Inventario.class);
                                        startActivity(intent);
                                    } else if (selectedOption.equals("Producto")) {
                                        Intent intent = new Intent(MainActivity.this, Producto.class);
                                        startActivity(intent);
                                    }
                                } else {
                                    // Autenticación fallida, mostrar mensaje de error
                                    Toast.makeText(MainActivity.this, "Error de autenticación. Verifica el correo y la contraseña.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
