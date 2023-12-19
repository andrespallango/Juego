package com.example.juego;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Juego extends AppCompatActivity {
    private ImageView cartaSeleccionada = null;
    private int cartasVolteadas = 0;
    private boolean bloquearInput = false;
    private List<String> todasLasCartas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Crear una lista de vocales
        List<String> vocales = Arrays.asList("a", "e", "i", "o", "u");

        // Mezclar las vocales para obtener pares aleatorios
        Collections.shuffle(vocales);

        // Seleccionar dos vocales aleatorias
        String vocal1 = vocales.get(0);
        String vocal2 = vocales.get(1);

        // Crear dos pares de cartas con las vocales seleccionadas
        List<String> dosParesVocales = new ArrayList<>();
        dosParesVocales.add(vocal1);
        dosParesVocales.add(vocal1);
        dosParesVocales.add(vocal2);
        dosParesVocales.add(vocal2);

        // Mezclar la lista para asignar vocales aleatorias a las cartas
        Collections.shuffle(dosParesVocales);

        // Asignar las vocales a las cartas en el GridLayout
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View childView = gridLayout.getChildAt(i);

            if (childView instanceof ImageView) {
                ImageView carta = (ImageView) childView;
                String etiqueta = dosParesVocales.get(i);
                carta.setTag(etiqueta);
                carta.setImageResource(R.drawable.reversa);
            }
        }

        // Guardar la lista de todas las cartas para verificación posterior
        todasLasCartas = new ArrayList<>(dosParesVocales);
        todasLasCartas.addAll(dosParesVocales);
        Log.d("Juego", "Lista de todas las cartas: " + todasLasCartas.toString());
    }

    public void voltearCarta(View view) {
        if (bloquearInput) {
            return;
        }

        ImageView carta = (ImageView) view;

        if (carta.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.reversa).getConstantState()) {
            return;
        }

        carta.setImageResource(obtenerIdImagen(carta.getTag().toString()));

        if (cartaSeleccionada == null) {
            cartaSeleccionada = carta;
        } else {
            bloquearInput = true;

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (cartaSeleccionada.getTag().equals(carta.getTag())) {
                    cartasVolteadas += 4;
                    if (cartasVolteadas == todasLasCartas.size()) {
                        Log.d("Juego", "Todas las cartas se han volteado, iniciando actividad Ganaste");
                        Intent intent = new Intent(Juego.this, Ganaste.class);
                        startActivity(intent);
                        finish(); // Finaliza la actividad actual
                    }
                } else {
                    carta.setImageResource(R.drawable.reversa);
                    cartaSeleccionada.setImageResource(R.drawable.reversa);
                }

                cartaSeleccionada = null;
                bloquearInput = false;
            }, 1000);
        }
    }

    private int obtenerIdImagen(String etiqueta) {
        switch (etiqueta) {
            case "a":
                return R.drawable.aletra;
            case "e":
                return R.drawable.eletra;
            case "i":
                return R.drawable.iletra;
            case "o":
                return R.drawable.oletra;
            case "u":
                return R.drawable.uletra;
            default:
                return 0;
        }
    }
}
