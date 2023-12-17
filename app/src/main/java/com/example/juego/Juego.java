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
        List<String> etiquetas = Arrays.asList("aletra", "aletra", "eletra", "eletra");
        Collections.shuffle(etiquetas);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View childView = gridLayout.getChildAt(i);

            if (childView instanceof ImageView) {
                ImageView carta = (ImageView) childView;
                String etiqueta = etiquetas.get(i);
                carta.setTag(etiqueta);
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
                    cartasVolteadas += 2;
                    if (cartasVolteadas == 4) {
                        Intent intent = new Intent(Juego.this, Ganaste.class);
                        startActivity(intent);
                    }
                } else {
                    carta.setImageResource(R.drawable.reversa);
                    cartaSeleccionada.setImageResource(R.drawable.reversa);
                }

                // Restablece la carta seleccionada y desbloquea el input
                cartaSeleccionada = null;
                bloquearInput = false;
            }, 1000); //
        }
    }

    private int obtenerIdImagen(String etiqueta) {
        if ("aletra".equals(etiqueta)) {
            return R.drawable.aletra; //
        } else if ("eletra".equals(etiqueta)) {
            return R.drawable.eletra; //
        }
        return 0;
    }
}
