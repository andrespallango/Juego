package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class JuegoConectar extends AppCompatActivity {

    Button btnA, btnE, btnI, btnO, btnU;
    ImageView linea_1, linea_2, linea_3, linea_4, linea_5;

    // Variables para realizar un seguimiento de las letras presionadas
    private boolean aPresionada = false;
    private boolean ePresionada = false;
    private boolean iPresionada = false;
    private boolean oPresionada = false;
    private boolean uPresionada = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_conectar);

        btnA = findViewById(R.id.bt_A);
        btnE = findViewById(R.id.bt_E);
        btnI = findViewById(R.id.bt_I);
        btnO = findViewById(R.id.bt_O);
        btnU = findViewById(R.id.bt_U);

        linea_1 = findViewById(R.id.linea);
        linea_2 = findViewById(R.id.linea2);
        linea_3 = findViewById(R.id.linea3);
        linea_4 = findViewById(R.id.linea4);
        linea_5 = findViewById(R.id.linea5);

        linea_1.setVisibility(View.INVISIBLE);
        linea_2.setVisibility(View.INVISIBLE);
        linea_3.setVisibility(View.INVISIBLE);
        linea_4.setVisibility(View.INVISIBLE);
        linea_5.setVisibility(View.INVISIBLE);

        // Configurar OnClickListener para cada botón
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarLetraPresionada('A', btnA);
            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarLetraPresionada('E', btnE);
            }
        });

        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarLetraPresionada('I', btnI);
            }
        });

        btnO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarLetraPresionada('O', btnO);
            }
        });

        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarLetraPresionada('U', btnU);
            }
        });
    }

    // Método para verificar la letra presionada
    private void verificarLetraPresionada(char letra, Button boton) {
        switch (letra) {
            case 'A':
                if (!aPresionada && !ePresionada) {
                    aPresionada = true;
                    // Habilitar el botón 'E'
                    btnE.setEnabled(true);
                }
                break;
            case 'E':
                if (aPresionada && !ePresionada) {
                    ePresionada = true;
                    // Deshabilitar los botones 'A' y 'E'
                    btnA.setClickable(false);
                    btnE.setClickable(false);
                    // Mostrar la línea 1
                    linea_1.setVisibility(View.VISIBLE);
                }
                break;
            case 'I':
                if (aPresionada && ePresionada && !iPresionada) {
                    iPresionada = true;
                    // Mostrar la línea 2
                    linea_2.setVisibility(View.VISIBLE);
                }
                break;
            case 'O':
                if (aPresionada && ePresionada && iPresionada && !oPresionada) {
                    oPresionada = true;
                    // Mostrar la línea 3
                    linea_3.setVisibility(View.VISIBLE);
                }
                break;
            case 'U':
                if (aPresionada && ePresionada && iPresionada && oPresionada && !uPresionada) {
                    uPresionada = true;

                    linea_4.setVisibility(View.VISIBLE);
                    linea_5.setVisibility(View.VISIBLE);
                    // Deshabilitar el botón 'U'
                    btnU.setEnabled(false);


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(JuegoConectar.this, GanasteU.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 1000);
                }
                break;
        }
    }
}