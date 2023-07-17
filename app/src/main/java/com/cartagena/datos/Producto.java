package com.cartagena.datos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Producto extends AppCompatActivity {

    private EditText etCodigo, etNombreP, etStock, etCosto, etPrecio;
    private Button btnBuscar1, btnActualizar1, btnBorrar1, btnGuardar1, btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        // Asociar los campos de texto y botones con sus identificadores en el archivo XML
        etCodigo = findViewById(R.id.etCodigo);
        etNombreP = findViewById(R.id.etNombreP);
        etStock = findViewById(R.id.etStock);
        etCosto = findViewById(R.id.etCosto);
        etPrecio = findViewById(R.id.etPrecio);

        btnBuscar1 = findViewById(R.id.btnBuscar1);
        btnActualizar1 = findViewById(R.id.btnActaulizar1);
        btnBorrar1 = findViewById(R.id.btnBorrar1);
        btnGuardar1 = findViewById(R.id.btnGuardar1);
        btnMenu = findViewById(R.id.btnMenu);

        // Agregar el Listener para el botón Buscar
        btnBuscar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = etCodigo.getText().toString();
                String nombre = etNombreP.getText().toString();
                String stock = etStock.getText().toString();
                String costo = etCosto.getText().toString();
                String precio = etPrecio.getText().toString();

                // Aquí podrías implementar la lógica para buscar los datos en algún medio (base de datos, archivo, etc.).
                // Por ahora, simplemente mostraremos un mensaje con los datos ingresados.
                String mensaje = "Código: " + codigo + "\nNombre: " + nombre + "\nStock: " + stock + "\nCosto: " + costo + "\nPrecio: " + precio;
                showToast(mensaje);
            }
        });

        // Agregar el Listener para el botón Actualizar
        btnActualizar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí podrías implementar la lógica para actualizar los datos en algún medio (base de datos, archivo, etc.).
                // Por ahora, simplemente mostraremos un mensaje de éxito.
                showToast("Datos actualizados con éxito");
            }
        });

        // Agregar el Listener para el botón Borrar
        btnBorrar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí podrías implementar la lógica para eliminar los datos en algún medio (base de datos, archivo, etc.).
                // Por ahora, simplemente mostraremos un mensaje de éxito.
                showToast("Datos eliminados con éxito");
                clearFields();
            }
        });

        // Agregar el Listener para el botón Guardar
        btnGuardar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí podrías implementar la lógica para guardar los datos en algún medio (base de datos, archivo, etc.).
                // Por ahora, simplemente mostraremos un mensaje de éxito.
                showToast("Datos guardados con éxito");
            }
        });

        // Agregar el Listener para el botón Menu
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para redireccionar a MainActivity
                Intent intent = new Intent(Producto.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clearFields() {
        // Limpiar los campos de texto
        etCodigo.setText("");
        etNombreP.setText("");
        etStock.setText("");
        etCosto.setText("");
        etPrecio.setText("");
    }

    private void showToast(String message) {
        // Mostrar un mensaje emergente (toast) con el texto pasado como parámetro
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
