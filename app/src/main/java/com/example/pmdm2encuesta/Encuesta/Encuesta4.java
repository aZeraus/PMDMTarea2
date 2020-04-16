package com.example.pmdm2encuesta.Encuesta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import com.example.pmdm2encuesta.AcercaDe;
import com.example.pmdm2encuesta.Ayuda;
import com.example.pmdm2encuesta.R;

public class Encuesta4 extends AppCompatActivity {
    int fallosP4 =0;
    int edad;
    String genero, provincia;
    String resp1, resp2, resp3, resp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta4);
        // Recogemos las variables de la actividad anterior.
        Intent intentRecibir = getIntent();
        int mEdad = (int)intentRecibir.getExtras().get("miEdad");
        String mGenero = (String) intentRecibir.getExtras().get("miGenero");
        String mProvincia = (String) intentRecibir.getExtras().get("miProvincia");
        String mResp1 = (String) intentRecibir.getExtras().get("miResp1");
        String mResp2 = (String) intentRecibir.getExtras().get("miResp2");
        String mResp3 = (String) intentRecibir.getExtras().get("miResp3");
        edad = mEdad;
        genero = mGenero;
        provincia = mProvincia;
        resp1 = mResp1;
        resp2 = mResp2;
        resp3 = mResp3;
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
     * Método que comprueba que haya algún género seleccionado y guarda su valor en el atributo
     * genero.
     */
    private void compruebaPregunta() {
        String cad4 = "";
        CheckBox cbp41, cbp42, cbp43, cbp44;
        int cont = 0;

        cbp41 = (CheckBox) findViewById(R.id.cb4a);
        cbp42 = (CheckBox) findViewById(R.id.cb4b);
        cbp43 = (CheckBox) findViewById(R.id.cb4c);
        cbp44 = (CheckBox) findViewById(R.id.cb4d);
        // Comprobamos todos los CheckBox por si hay alguno seleccionado.
        if (cbp41.isChecked()){// Si no asignamos el resultado en función del seleccionado.
            cad4 += "a. Smartphone.\n";
            cont ++;
        }
        if (cbp42.isChecked()){
            cad4 += "b. Tablet.\n";
            cont ++;
        }
        if (cbp43.isChecked()){
            cad4 += "c. Ordenador personal.\n";
            cont ++;
        }
        if (cbp44.isChecked()){
            cad4 += "d. Otros.\n";
            cont ++;
        }
        resp4 = cad4;
        // Si no hay ninguno seleccionado marcamos un error.
        if (cont==0) fallosP4 ++;
    }

    /**
     * Método para que el botón Pregunta4 compruebe que tenga una opción seleccionada y si es así
     * nos lleva a la siguiente pregunta.
     * Llama al método compruebaPregunta.
     * Si hay algún error lanza un mensaje indicándolo y si no abre la siguiente pantalla.
     */
    public void Pregunta5(View view) {

        // Llamamos al método para comprobar que el género esté seleccionado.
        compruebaPregunta();
        // Si hay aluguna opción seleccionada va a la siguiente pantalla.
        if (fallosP4 == 0) {
            Intent siguientePregunta = new Intent(this, Encuesta5.class);
            // Enviamos las variables a la siguiente actividad.
            siguientePregunta.putExtra("miEdad", edad);
            siguientePregunta.putExtra("miGenero", genero);
            siguientePregunta.putExtra("miProvincia", provincia);
            siguientePregunta.putExtra("miResp1", resp1);
            siguientePregunta.putExtra("miResp2", resp2);
            siguientePregunta.putExtra("miResp3", resp3);
            siguientePregunta.putExtra("miResp4", resp4);
            startActivity(siguientePregunta);
        } else { // Si no lanza un mensaje.
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.errNoSeleccionado), Toast.LENGTH_LONG).show();
            fallosP4 = 0;
        }
    }
}