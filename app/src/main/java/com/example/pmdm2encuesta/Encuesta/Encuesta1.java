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

public class Encuesta1 extends AppCompatActivity {
    int fallosP1 =0;
    int edad;
    String genero, provincia;
    String resp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta1);
        // Recogemos las variables de la actividad anterior.
        Intent intentRecibir = getIntent();
        int mEdad = (int)intentRecibir.getExtras().get("miEdad");
        String mGenero = (String) intentRecibir.getExtras().get("miGenero");
        String mProvincia = (String) intentRecibir.getExtras().get("miProvincia");
        edad = mEdad;
        genero = mGenero;
        provincia = mProvincia;
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
        String cad1 = "";
        RadioButton rbp11, rbp12, rbp13, rbp14;

        rbp11 = (RadioButton) findViewById(R.id.rb1a);
        rbp12 = (RadioButton) findViewById(R.id.rb1b);
        rbp13 = (RadioButton) findViewById(R.id.rb1c);
        rbp14 = (RadioButton) findViewById(R.id.rb1d);

        // Si queremos comprobar si alguno de los RadioButton de un RadioGroup ha sido seleccionado:
        RadioGroup rgPregunta1 = (RadioGroup) findViewById(R.id.rgPregunta1);

        // Si el indice de seleccionados vale -1 es que no se ha seleccionado ninguno.
        if (rgPregunta1.getCheckedRadioButtonId() == -1) {
            fallosP1++;
        } else if (rbp11.isChecked()){// Si no asignamos el resultado en función del seleccionado.
            cad1 += "a. Menos de un mes.\n";
        } else if (rbp12.isChecked()){
            cad1 += "b. Entre seis meses y un año.\n";
        } else if (rbp13.isChecked()){
            cad1 += "c. Entre uno y tres años.\n";
        } else if (rbp14.isChecked()){
            cad1 += "d. Más de tres años.\n";
        }
        resp1 = cad1;
    }

    /**
     * Método para que el botón Pregunta2 compruebe que tenga una opción seleccionada y si es así
     * nos lleva a la siguiente pregunta.
     * Llama al método compruebaPregunta.
     * Si hay algún error lanza un mensaje indicándolo y si no abre la siguiente pantalla.
     */
    public void Pregunta2(View view) {

        // Llamamos al método para comprobar que el género esté seleccionado.
        compruebaPregunta();
        // Si hay aluguna opción seleccionada va a la siguiente pantalla.
        if (fallosP1 == 0) {
            Intent siguientePregunta = new Intent(this, Encuesta2.class);
            // Enviamos las variables a la siguiente actividad.
            siguientePregunta.putExtra("miEdad", edad);
            siguientePregunta.putExtra("miGenero", genero);
            siguientePregunta.putExtra("miProvincia", provincia);
            siguientePregunta.putExtra("miResp1", resp1);
            startActivity(siguientePregunta);
        } else { // Si no lanza un mensaje.
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.errNoSeleccionado), Toast.LENGTH_LONG).show();
            fallosP1 = 0;
        }
    }
}