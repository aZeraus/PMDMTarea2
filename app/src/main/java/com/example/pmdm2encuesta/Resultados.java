package com.example.pmdm2encuesta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Resultados extends AppCompatActivity {
    int edad;
    String genero, provincia;
    String resp1, resp2, resp3, resp4, resp5,resp6, resp7, resp8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        // Recogemos las variables de la actividad anterior.
        Intent intentRecibir = getIntent();
        int mEdad = (int) intentRecibir.getExtras().get("miEdad");
        String mGenero = (String) intentRecibir.getExtras().get("miGenero");
        String mProvincia = (String) intentRecibir.getExtras().get("miProvincia");
        String mResp1 = (String) intentRecibir.getExtras().get("miResp1");
        String mResp2 = (String) intentRecibir.getExtras().get("miResp2");
        String mResp3 = (String) intentRecibir.getExtras().get("miResp3");
        String mResp4 = (String) intentRecibir.getExtras().get("miResp4");
        String mResp5 = (String) intentRecibir.getExtras().get("miResp5");
        String mResp6 = (String) intentRecibir.getExtras().get("miResp6");
        String mResp7 = (String) intentRecibir.getExtras().get("miResp7");
        String mResp8 = (String) intentRecibir.getExtras().get("miResp8");
        edad = mEdad;
        genero = mGenero;
        provincia = mProvincia;
        resp1 = mResp1;
        resp2 = mResp2;
        resp3 = mResp3;
        resp4 = mResp4;
        resp5 = mResp5;
        resp6 = mResp6;
        resp7 = mResp7;
        resp8 = mResp8;

        // Rellenamos los datos.
        TextView tvrEdad = findViewById(R.id.tvResEdad);
        tvrEdad.setText(edad + " años.");
        TextView tvrGenero = findViewById(R.id.tvResGenero);
        tvrGenero.setText(genero);
        TextView tvrProvincia = findViewById(R.id.tvResProvincia);
        tvrProvincia.setText(provincia);

        // Rellenamos las preguntas.
        TextView tvrP1 = findViewById(R.id.tvResP1);
        tvrP1.setText(resp1);
        TextView tvrP2 = findViewById(R.id.tvResP2);
        tvrP2.setText(resp2);
        TextView tvrP3 = findViewById(R.id.tvResP3);
        tvrP3.setText(resp3);
        TextView tvrP4 = findViewById(R.id.tvResP4);
        tvrP4.setText(resp4);
        TextView tvrP5 = findViewById(R.id.tvResP5);
        tvrP5.setText(resp5);
        TextView tvrP6 = findViewById(R.id.tvResP6);
        tvrP6.setText(resp6);
        TextView tvrP7 = findViewById(R.id.tvResP7);
        tvrP7.setText(resp7);
        TextView tvrP8 = findViewById(R.id.tvResP8);
        tvrP8.setText(resp8);

        // Rellenamos la URL
        TextView tvrURL = findViewById(R.id.tvResURL);
        tvrURL.setText("http://com.example.pmdm2encuesta/receptor.php?edad=" + edad +
                "&genero=" + genero + "&provincia=" + provincia + "&test1=" + resp1 + "&test2=" +
                resp2 + "&test3=" + resp3 + "&test4=" + resp4 + "&test5=" + resp5 + "&test6=" +
                resp6 + "&test7=" + resp7 + "&test8=" + resp8);
    }

    /**
     * Método para crear el menú
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Método para crear los items del menú
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ayuda:
                Intent intentAyuda = new Intent(this, Ayuda.class);
                startActivity(intentAyuda);
                return true;
            case R.id.acercaDe:
                Intent intentAcerca = new Intent(this, AcercaDe.class);
                startActivity(intentAcerca);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /**
     * Método para que el botón Volver vuelva a la pantalla principal.
     */
    public void Volver (View view) {
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);
    }
}