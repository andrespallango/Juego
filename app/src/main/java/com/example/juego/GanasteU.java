// Ganaste.java
package com.example.juego;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GanasteU extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganasteu);

        // Obtén referencia al botón para volver a MainActivity
        Button btnVolverMenu = findViewById(R.id.bt_volver_menu);

        // Configura el listener del botón para volver a MainActivity
        btnVolverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para volver a la actividad principal (MainActivity)
                Intent intent = new Intent(GanasteU.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); // Añade esta línea
                startActivity(intent);
            }
        });

        // Obtén referencia al botón para volver a Juego
        Button btnVolverAJugar = findViewById(R.id.btnVolverAJugar);

        // Configura el listener del botón para volver a Juego
        btnVolverAJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para volver a jugar
                Intent intent = new Intent(GanasteU.this, JuegoConectar.class);
                startActivity(intent);
                finish();  // Cierra la actividad actual
            }
        });
    }
}
