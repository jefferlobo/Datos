package com.cartagena.datos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Compras extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        Button btnPagar2 = findViewById(R.id.btnPagar2);
        btnPagar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Compras.this, "La paga se ha realizado", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnComprar = findViewById(R.id.btnComprar);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Compras.this, "Usted ha comprado un producto pera", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
