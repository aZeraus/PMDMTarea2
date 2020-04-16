package com.example.pmdm2encuesta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Ayuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
    }
    /**
     * Método para cerrar la ventana
     *
     * @param view View por parametros
     */
    public void volverAyuda(View view) {
        finish();
    }
}
