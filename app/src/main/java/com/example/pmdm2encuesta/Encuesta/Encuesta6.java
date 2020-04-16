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

public class Encuesta6 extends AppCompatActivity {
    int fallosP6 =0;
    int edad;
    String genero, provincia;
    String resp1, resp2, resp3, resp4, resp5,resp6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta6);
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
        edad = mEdad;
        genero = mGenero;
        provincia = mProvincia;
        resp1 = mResp1;
        resp2 = mResp2;
        resp3 = mResp3;
        resp4 = mResp4;
        resp5 = mResp5;
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
        String cad6 = "";
        RadioButton rbp61, rbp62, rbp63, rbp64;

        rbp61 = (RadioButton) findViewById(R.id.rb6a);
        rbp62 = (RadioButton) findViewById(R.id.rb6b);
        rbp63 = (RadioButton) findViewById(R.id.rb6c);
        rbp64 = (RadioButton) findViewById(R.id.rb6d);

        // Si queremos comprobar si alguno de los RadioButton de un RadioGroup ha sido seleccionado:
        RadioGroup rgPregunta1 = (RadioGroup) findViewById(R.id.rgPregunta6);

        // Si el indice de seleccionados vale -1 es que no se ha seleccionado ninguno.
        if (rgPregunta1.getCheckedRadioButtonId() == -1) {
            fallosP6++;
        } else if (rbp61.isChecked()){// Si no asignamos el resultado en función del seleccionado.
            cad6 += "a. No lo utilizo, es un medio obsoleto que está condenado a desaparecer.\n";
        } else if (rbp62.isChecked()){
            cad6 += "b. No lo utilizo, pero varias personas cercanas a mi siguen utilizando este formato.\n";
        } else if (rbp63.isChecked()){
            cad6 += "c. Lo utilizo en ocasiones puntuales.\n";
        } else if (rbp64.isChecked()){
            cad6 += "d. Lo utilizo a diario y me parece mucho mejor que el formato electrónico.\n";
        }
        resp6 = cad6;
    }

    /**
     * Método para que el botón Pregunta6 compruebe que tenga una opción seleccionada y si es así
     * nos lleva a la siguiente pregunta.
     * Llama al método compruebaPregunta.
     * Si hay algún error lanza un mensaje indicándolo y si no abre la siguiente pantalla.
     */
    public void Pregunta7(View view) {

        // Llamamos al método para comprobar que el género esté seleccionado.
        compruebaPregunta();
        // Si hay aluguna opción seleccionada va a la siguiente pantalla.
        if (fallosP6 == 0) {
            Intent siguientePregunta = new Intent(this, Encuesta7.class);
            // Enviamos las variables a la siguiente actividad.
            siguientePregunta.putExtra("miEdad", edad);
            siguientePregunta.putExtra("miGenero", genero);
            siguientePregunta.putExtra("miProvincia", provincia);
            siguientePregunta.putExtra("miResp1", resp1);
            siguientePregunta.putExtra("miResp2", resp2);
            siguientePregunta.putExtra("miResp3", resp3);
            siguientePregunta.putExtra("miResp4", resp4);
            siguientePregunta.putExtra("miResp5", resp5);
            siguientePregunta.putExtra("miResp6", resp6);
            startActivity(siguientePregunta);
        } else { // Si no lanza un mensaje.
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.errNoSeleccionado), Toast.LENGTH_LONG).show();
            fallosP6 = 0;
        }
    }
}
