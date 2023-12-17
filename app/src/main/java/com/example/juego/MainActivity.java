package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {
    Button btnJugar, btnSalir;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnJugar = findViewById(R.id.bt_jugar);
        btnSalir = findViewById(R.id.bt_salir);

        // Verifica si ya hay una instancia de MediaPlayer
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.fondo);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Detener la reproducción de música
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }

                // Cierra toda la aplicación
                finishAffinity();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // No detengas el MediaPlayer aquí, ya que queremos mantener la música en segundo plano.
    }
}
