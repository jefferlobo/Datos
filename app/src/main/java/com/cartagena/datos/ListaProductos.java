package com.cartagena.datos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListaProductos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        TextView texto1 = findViewById(R.id.texto1);
        TextView texto2 = findViewById(R.id.texto2);

        // Establecer el texto con los datos deseados para "texto1"
        String codigo = "COD:123";
        String nombreProducto = "Nombre del producto: pera";
        String stock = "Stock: 30";
        String precioVenta = "Precio de venta: 4";

        // Unir los datos en una sola cadena con saltos de línea ("\n")
        String datosCompletos = codigo + "\n" + nombreProducto + "\n" + stock + "\n" + precioVenta;

        // Establecer el texto en el TextView "texto1"
        texto1.setText(datosCompletos);
        String codigo2 = "COD:1234";
        String nombreProducto2 = "Nombre del producto: manzana";
        String stock2 = "Stock: 29";
        String precioVenta2 = "Precio de venta: 8";

        // Unir los nuevos datos en una sola cadena con saltos de línea ("\n")
        String datosCompletos2 = codigo2 + "\n" + nombreProducto2 + "\n" + stock2 + "\n" + precioVenta2;

        // Establecer el texto en el TextView "texto2"
        texto2.setText(datosCompletos2);


    }
}
