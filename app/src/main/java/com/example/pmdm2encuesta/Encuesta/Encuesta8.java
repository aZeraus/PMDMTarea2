package com.example.pmdm2encuesta.Encuesta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.pmdm2encuesta.AcercaDe;
import com.example.pmdm2encuesta.Ayuda;
import com.example.pmdm2encuesta.R;
import com.example.pmdm2encuesta.Resultados;

public class Encuesta8 extends AppCompatActivity {
    int fallosP8 =0;
    int edad;
    String genero, provincia;
    String resp1, resp2, resp3, resp4, resp5,resp6, resp7, resp8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta8);
        // Recogemos las variables de la actividad anterior.
        Intent intentRecibir = getIntent();
        int mEdad = (int)intentRecibir.getExtras().get("miEdad");
        String mGenero = (String) intentRecibir.getExtras().get("miGenero");
        String mProvincia = (String) intentRecibir.getExtras().get("miProvincia");
        String mResp1 = (String) intentRecibir.getExtras().get("miResp1");
        String mResp2 = (String) intentRecibir.getExtras().get("miResp2");
        String mResp3 = (String) intentRecibir.getExtras().get("miResp3");
        String mResp4 = (String) intentRecibir.getExtras().get("miResp4");
        String mResp5 = (String) intentRecibir.getExtras().get("miResp5");
        String mResp6 = (String) intentRecibir.getExtras().get("miResp6");
        String mResp7 = (String) intentRecibir.getExtras().get("miResp7");
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
        String cad8 = "";
        RadioButton rbp81, rbp82, rbp83, rbp84;

        rbp81 = (RadioButton) findViewById(R.id.rb8a);
        rbp82 = (RadioButton) findViewById(R.id.rb8b);
        rbp83 = (RadioButton) findViewById(R.id.rb8c);
        rbp84 = (RadioButton) findViewById(R.id.rb8d);

        // Si queremos comprobar si alguno de los RadioButton de un RadioGroup ha sido seleccionado:
        RadioGroup rgPregunta1 = (RadioGroup) findViewById(R.id.rgPregunta8);

        // Si el indice de seleccionados vale -1 es que no se ha seleccionado ninguno.
        if (rgPregunta1.getCheckedRadioButtonId() == -1) {
            fallosP8++;
        } else if (rbp81.isChecked()){// Si no asignamos el resultado en función del seleccionado.
            cad8 += "a. Seguro que sí\n";
        } else if (rbp82.isChecked()){
            cad8 += "b. Probablemente sí.\n";
        } else if (rbp83.isChecked()){
            cad8 += "c. Probablemente no.\n";
        } else if (rbp84.isChecked()){
            cad8 += "d. Seguro que no.\n";
        }
        resp8 = cad8;
    }

    /**
     * Método para que el botón Pregunta5 compruebe que tenga una opción seleccionada y si es así
     * nos lleva a la siguiente pregunta.
     * Llama al método compruebaPregunta.
     * Si hay algún error lanza un mensaje indicándolo y si no abre la siguiente pantalla.
     */
    public void Fin(View view) {

        // Llamamos al método para comprobar que el género esté seleccionado.
        compruebaPregunta();
        // Si hay aluguna opción seleccionada va a la siguiente pantalla.
        if (fallosP8 == 0) {
            Intent fin = new Intent(this, Resultados.class);
            // Enviamos las variables a la siguiente actividad.
            fin.putExtra("miEdad", edad);
            fin.putExtra("miGenero", genero);
            fin.putExtra("miProvincia", provincia);
            fin.putExtra("miResp1", resp1);
            fin.putExtra("miResp2", resp2);
            fin.putExtra("miResp3", resp3);
            fin.putExtra("miResp4", resp4);
            fin.putExtra("miResp5", resp5);
            fin.putExtra("miResp6", resp6);
            fin.putExtra("miResp7", resp7);
            fin.putExtra("miResp8", resp8);
            startActivity(fin);
        } else { // Si no lanza un mensaje.
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.errNoSeleccionado), Toast.LENGTH_LONG).show();
            fallosP8 = 0;
        }
    }
}

