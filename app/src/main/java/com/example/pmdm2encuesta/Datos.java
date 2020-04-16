package com.example.pmdm2encuesta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.pmdm2encuesta.Encuesta.Encuesta1;

public class Datos extends AppCompatActivity {
    int fallos=0;
    int edad;
    String genero;
    String provincia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        // Array que asociaremos al adaptador
        String[] datos = new String[] {
                "Almería"
                ,"Granada"
                ,"Jaen"
        };

        // Creamos el adaptador
        ArrayAdapter <String> adaptador = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, datos);

        // Asociamos el adaptador a la vista.
        Spinner spnProvincias = (Spinner) findViewById(R.id.spnProvincias);
        spnProvincias.setAdapter(adaptador);
        // Nos aseguramos que no haya nada seleccionado para obligar a que se seleccione algo.
        spnProvincias.setSelection(-1);
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
     * Método que comprueba que la edad esté entre 16 y 120 años y guarda su valor en el atributo edad.
     */
    private void compruebaEdad() {
        edad = 0;
        EditText etEdad = findViewById(R.id.etEdad);
        edad = Integer.parseInt(etEdad.getText().toString());
        if (edad < 16 || edad > 120) {
            fallos++;
        }
    }

    /**
     * Método que comprueba que haya algún género seleccionado y guarda su valor en el atributo
     * genero.
     */
    private void compruebaGenero() {
        genero = "";
        RadioButton rbH, rbM, rbO;
        rbH = (RadioButton) findViewById(R.id.rbHombre);
        rbM = (RadioButton) findViewById(R.id.rbMujer);
        rbO = (RadioButton) findViewById(R.id.rbOtro);

        // Si queremos comprobar si alguno de los RadioButton de un RadioGroup ha sido seleccionado:
        RadioGroup grupoGenero = (RadioGroup) findViewById(R.id.rgGenero);

        // Si el indice de seleccionados vale -1 es que no se ha seleccionado ninguno.
        if (grupoGenero.getCheckedRadioButtonId() == -1) {
            fallos++;
        } else if (rbH.isChecked()){// Si no asignamos el género en función del seleccionado.
            genero = "Hombre";
        } else if (rbM.isChecked()){
            genero = "Mujer";
        } else genero = "Otro";
    }

    /**
     * Método que comprueba que haya alguna provincia seleccionada y guarda su valor en el atributo
     * provincia.
     */
    private void compruebaProvincia() {
        // Obtenemos el índice de la provincia seleccionada.
        Spinner spnProv = (Spinner) findViewById(R.id.spnProvincias);
        provincia = spnProv.getSelectedItem().toString();
        if (provincia!="Almería" && provincia!="Granada" && provincia!="Jaen"){
            fallos ++;
        }
    }

    /**
     * Método para que el botón Continuar compruebe que está todo relleno y si lo está nos lleve a
     * la siguiente pantalla.
     * Llama a los métodos compruebaGenero, compruebaEdad y compruebaProvincia.
     * Si hay algún error lanza un mensaje indicándolo y si no abre la siguiente pantalla.
     */
    public void Continuar(View view) {

        // Llamamos al método para comprobar que la edad esté rellena.
        compruebaEdad();
        // Llamamos al método para comprobar que el género esté seleccionado.
        compruebaGenero();
        // Llamamos al método para comprobar que la provincia esté seleccionada.
        compruebaProvincia();
        // Si todo está correcto va a la siguiente pantalla
        if (fallos == 0){
            Intent continuar = new Intent(this, Encuesta1.class);
            // Enviamos las variables a la siguiente actividad.
            continuar.putExtra("miEdad", edad);
            continuar.putExtra("miGenero", genero);
            continuar.putExtra("miProvincia", provincia);
            startActivity(continuar);
        } else{ // Si no lanza un mensaje.
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.errNoCompleto), Toast.LENGTH_LONG).show();
            fallos = 0;
        }
    }
}
