package com.cartagena.datos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Inventario extends AppCompatActivity {

    private Spinner spinnerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        spinnerMenu = findViewById(R.id.spinnerMenu);

        String[] opciones = {"Menu", "Ventas", "Compras", "Lista de Productos", "Salir"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMenu.setAdapter(adapter);

        spinnerMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = (String) parent.getItemAtPosition(position);
                switch (selectedOption) {
                    case "Ventas":
                        startActivity(new Intent(Inventario.this, Ventas.class));
                        break;
                    case "Compras":
                        startActivity(new Intent(Inventario.this, Compras.class));
                        break;
                    case "Lista de Productos":
                        startActivity(new Intent(Inventario.this, ListaProductos.class));
                        break;
                    case "Salir":
                        finish();
                        break;
                    // Por defecto, no hacemos nada si se selecciona "Menu"
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacemos nada en este caso
            }
        });
    }
}
