// Juego.java
package com.example.juego;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Juego extends AppCompatActivity {
    private ImageView cartaSeleccionada = null;
    private int cartasVolteadas = 0;
    private boolean bloquearInput = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Crear una lista con dos etiquetas "aletra" y dos etiquetas "eletra"
        List<String> etiquetas = Arrays.asList("aletra", "aletra", "eletra", "eletra");
        Collections.shuffle(etiquetas);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View childView = gridLayout.getChildAt(i);

            if (childView instanceof ImageView) {
                ImageView carta = (ImageView) childView;

                // Asignar la etiqueta correspondiente de la lista
                String etiqueta = etiquetas.get(i);
                carta.setTag(etiqueta);

                // Asignar la imagen de reversa.png como estado inicial
                carta.setImageResource(R.drawable.reversa);
            }
        }
    }

    public void voltearCarta(View view) {
        if (bloquearInput) {
            return;
        }

        ImageView carta = (ImageView) view;

        if (carta.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.reversa).getConstantState()) {
            // La carta ya está volteada, no hagas nada
            return;
        }

        carta.setImageResource(obtenerIdImagen(carta.getTag().toString())); // Voltea la carta

        if (cartaSeleccionada == null) {
            // Esta es la primera carta seleccionada
            cartaSeleccionada = carta;
        } else {
            bloquearInput = true; // Bloquea el input para evitar más clics durante la comparación

            // Esta es la segunda carta seleccionada
            // Compara las cartas
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (cartaSeleccionada.getTag().equals(carta.getTag())) {
                    // Las cartas son iguales, déjalas volteadas
                    cartasVolteadas += 2;
                    if (cartasVolteadas == 4) {
                        // Todas las cartas están volteadas, dirigirse a Ganaste.java
                        Intent intent = new Intent(Juego.this, Ganaste.class);
                        startActivity(intent);
                    }
                } else {
                    // Las cartas no son iguales, voltea ambas
                    carta.setImageResource(R.drawable.reversa);
                    cartaSeleccionada.setImageResource(R.drawable.reversa);
                }

                // Restablece la carta seleccionada y desbloquea el input
                cartaSeleccionada = null;
                bloquearInput = false;
            }, 1000); // Retraso antes de voltear las cartas
        }
    }

    private int obtenerIdImagen(String etiqueta) {
        // Devuelve el ID de la imagen según la etiqueta
        if ("aletra".equals(etiqueta)) {
            return R.drawable.aletra; // Reemplaza con el ID correcto de aletra.png
        } else if ("eletra".equals(etiqueta)) {
            return R.drawable.eletra; // Reemplaza con el ID correcto de eletra.png
        }
        return 0; // Puedes manejar un valor predeterminado o lanzar una excepción si es necesario
    }
}
