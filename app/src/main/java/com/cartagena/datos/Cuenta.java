package com.cartagena.datos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference; // ¡Importación faltante!

import com.google.firebase.database.FirebaseDatabase; // Importación para usar el Firebase Realtime Database

public class Cuenta extends AppCompatActivity implements View.OnClickListener {

    private EditText etCorreos;
    private EditText etPasswords;
    private Button btnGuardar;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference(); // Inicializar la referencia del Firebase Realtime Database

        etCorreos = findViewById(R.id.etCorreos);
        etPasswords = findViewById(R.id.etPasswords);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnLogin = findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(this);

        btnGuardar.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnGuardar) {
            guardarDatosEnFirebase();
        } else if (view == btnLogin) {
            // Aquí se ejecuta el código cuando se hace clic en el botón "btnLogin"
            // Redireccionar a la MainActivity
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void guardarDatosEnFirebase() {
        final String correo = etCorreos.getText().toString().trim();
        final String password = etPasswords.getText().toString().trim();

        // Verificar que la contraseña tenga al menos 6 caracteres
        if (password.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(correo)) {
            Toast.makeText(this, "Se debe ingresar un correo", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en línea...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Guardar datos adicionales en la base de datos de Firebase
                            String userId = firebaseAuth.getCurrentUser().getUid();
                            mDatabase.child("usuarios").child(userId).child("correo").setValue(correo);

                            Toast.makeText(Cuenta.this, "Se ha registrado el correo", Toast.LENGTH_LONG).show();
                        } else {
                            // Si ocurre un error al guardar los datos en Firebase Authentication
                            FirebaseAuthException e = (FirebaseAuthException) task.getException();
                            Toast.makeText(Cuenta.this, "No se pudo registrar el usuario: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}
