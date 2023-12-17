package com.example.juego;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnMemoria = findViewById(R.id.bt_memoria);
        Button btnConectarVocal = findViewById(R.id.bt_conectar_vocal);
        Button btnAtras = findViewById(R.id.bt_atras);

        btnMemoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lleva a la actividad de juego
                Intent intent = new Intent(Menu.this, Juego.class);
                startActivity(intent);
            }
        });

        btnConectarVocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lleva a la actividad de JuegoConectar
                Intent intent = new Intent(Menu.this, JuegoConectar.class);
                startActivity(intent);
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para regresar a la pantalla anterior
                finish();
            }
        });
    }
}
