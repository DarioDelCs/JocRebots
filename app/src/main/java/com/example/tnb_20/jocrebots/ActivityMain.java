package com.example.tnb_20.jocrebots;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


public class ActivityMain extends AppCompatActivity {

    ImageView img;

    float velocitatX = 4.0f;
    float velocitatY = 4.0f;

    int statusBar, width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // La bola
        img = (ImageView) findViewById(R.id.imageView);

        // Obtenim les dimensions de la pantalla
        DisplayMetrics display = this.getBaseContext().getResources().getDisplayMetrics();
        width = display.widthPixels;
        height = display.heightPixels;

        // Mida de l'statusBar per calcular l'alçada de l'aplicació
        statusBar = getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android"));

        img.setX(0f);
        img.setY(0f);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                moveBall(2, 2);
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 1000,30);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void moveBall(float x, float y) {
        // Coordenada x
        float novaPosicioX = img.getX() + x * velocitatX;
        if (img.getX() > 0) {// Si el moviment és cap a la dreta
            // Comprovem que no surti de les dimensions de la pantalla en assignar la nova posició
            if (novaPosicioX + img.getWidth() < width) {
                img.setX(novaPosicioX);
            } else {// Si en surt, establim la posició màxima en horitzontal perquè es pugui veure la imatge.
                //img.setX(novaPosicioX- img.getWidth());
                img.setX(width - img.getWidth());
                velocitatX=(-1)*velocitatX;
            }
        } else {// Fem el mateix pel moviment cap a l'esquerra
            if (novaPosicioX > 0) {
                img.setX(novaPosicioX);
            } else{
                img.setX(0);
                velocitatX=(-1)*velocitatX;
            }
        }

        // Coordenada y
        float novaPosicioY = img.getY() + y * velocitatY;
        // El concepte és el mateix que a la X
        if (img.getY() > 0) {
            if (novaPosicioY + img.getHeight() + statusBar < height) {//
                img.setY(novaPosicioY);
            } else {
                //img.setY(novaPosicioY*-1);
                img.setY(height - img.getHeight() - statusBar);
                velocitatY=(-1)*velocitatY;
            }
        }else {
            if (novaPosicioY > 0) {
                img.setY(novaPosicioY);
            } else {
                img.setY(0);
                velocitatY=(-1)*velocitatY;
            }
        }
    }
}
