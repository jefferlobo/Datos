package com.cartagena.datos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Persona extends AppCompatActivity {

    private EditText etCedula, etNombre, etCorreo, etProvincia;
    private RadioButton radioHombre, radioMujer;
    private Spinner spinnerPais;
    private Button btonActualizar, btonLogin;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Persona");

        etCedula = findViewById(R.id.etCedula);
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etProvincia = findViewById(R.id.etProvincia); // Agregamos el campo de provincia
        radioHombre = findViewById(R.id.radioHombre);
        radioMujer = findViewById(R.id.radioMujer);
        spinnerPais = findViewById(R.id.spinnerPais);
        btonActualizar = findViewById(R.id.btonActualizar);
        btonLogin = findViewById(R.id.btonLogin);

        // Lista de países
        String[] paises = {"Ecuador", "Perú", "México"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paises);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPais.setAdapter(adapter);

        btonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarDatosPersona();
            }
        });

        btonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Persona.this, MainActivity.class));
                finish();
            }
        });
    }

    private void actualizarDatosPersona() {
        final String cedula = etCedula.getText().toString().trim();
        final String nombre = etNombre.getText().toString().trim();
        final String correo = etCorreo.getText().toString().trim();
        final String provincia = etProvincia.getText().toString().trim();
        final boolean esHombre = radioHombre.isChecked();
        final boolean esMujer = radioMujer.isChecked();
        final String paisSeleccionado = spinnerPais.getSelectedItem().toString();
        final int valorPais;

        // Asignar el valor correspondiente al país seleccionado
        if (paisSeleccionado.equals("Ecuador")) {
            valorPais = 1;
        } else if (paisSeleccionado.equals("Perú")) {
            valorPais = 2;
        } else if (paisSeleccionado.equals("México")) {
            valorPais = 3;
        } else {
            valorPais = 0; // Valor por defecto si no se selecciona ningún país válido
        }

        // Obtener la referencia a la ubicación específica en la base de datos usando la cédula como identificador único
        DatabaseReference personaRef = mDatabase.child(cedula);

        personaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Verificar si la referencia existe en la base de datos
                if (dataSnapshot.exists()) {
                    // Crear un Map con los nuevos valores a actualizar
                    Map<String, Object> actualizacionPersona = new HashMap<>();
                    actualizacionPersona.put("etCedula", cedula);
                    actualizacionPersona.put("etNombre", nombre);
                    actualizacionPersona.put("etCorreo", correo);
                    actualizacionPersona.put("etProvincia", provincia); // Agregamos el campo provincia
                    actualizacionPersona.put("radioHombre", esHombre ? 1 : 0);
                    actualizacionPersona.put("radioMujer", esMujer ? 2 : 0);
                    actualizacionPersona.put("spinnerPais", valorPais);

                    personaRef.updateChildren(actualizacionPersona)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Persona.this, "Datos actualizados correctamente.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Persona.this, "Error al actualizar los datos.", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    // La referencia no existe en la base de datos
                    Toast.makeText(Persona.this, "La cédula ingresada no existe en la base de datos.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar la cancelación de la lectura de datos (opcional)
            }
        });
    }
}
