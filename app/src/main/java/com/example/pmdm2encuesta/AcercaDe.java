package com.example.pmdm2encuesta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class AcercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
    }
    /**
     * Método para cerrar la ventana
     *
     * @param view View por parametros
     */
    public void volverAcerca(View view) {
        finish();
    }
}
