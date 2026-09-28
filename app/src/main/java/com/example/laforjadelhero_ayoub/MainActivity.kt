package com.example.laforjadelhero_ayoub

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val TAG = "CreadorPersonajes"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Vinculamos el xml con el editText
        val etGuerrero = findViewById<EditText>(R.id.etGuerrero)
        // Para que gane el foco automaticamente
        etGuerrero.requestFocus()

        //Vinculamos el spinner
        val Raza =findViewById<Spinner>(R.id.Raza)

        // Vinculamos la vista de faccion
        val rgFaccion = findViewById<RadioGroup>(R.id.rgFaccion)

        // Vinculamos las vistas de habilidades
        val cbSigilo = findViewById<CheckBox>(R.id.casillavSigilo)
        val cbCombateEspada = findViewById<CheckBox>(R.id.casillavCombateEspada)

        // Vinculamos el boton
        val btnRegistrar = findViewById<ImageButton>(R.id.btnRegistrar)

        // Evento click mediante funcion lambda
        btnRegistrar.setOnClickListener {

            // Leemos la seleccion al pulsar el boton
            val RazaSeleccionada = Raza.selectedItem.toString()

            // Obtenemos el id del radiobuttton
            val selectedRadioButtonID = rgFaccion.checkedRadioButtonId
            val faccionseleccionada = if (selectedRadioButtonID != -1) {
                val rbseleccionado = findViewById<RadioButton>(selectedRadioButtonID)
                rbseleccionado.text.toString()
            } else { "Sin faccion"}

            // Comprobamos el estado
            val habilidadesSeleccionadas = mutableListOf<String>()

            if (cbSigilo.isChecked) { habilidadesSeleccionadas.add(cbSigilo.text.toString())  }

            if (cbCombateEspada.isChecked) {habilidadesSeleccionadas.add(cbCombateEspada.text.toString()) }

            // Convertimos la lista a  texto
            val textoHabilidades = if (habilidadesSeleccionadas.isNotEmpty())
            {
                habilidadesSeleccionadas.joinToString(", ")
            } else {"Ninguna"}

            // Lectura del nombre y construccion del resumen
            val nombre = etGuerrero.text.toString().ifEmpty { "Desconocido" }
            val resumenPersonaje = "Heroe: $nombre | Raza: $RazaSeleccionada | Faccion: $faccionseleccionada | Habilidades: $textoHabilidades"

            // Disparar Toast y registrar en Logcat
            Toast.makeText(this, resumenPersonaje, Toast.LENGTH_LONG).show()
            Log.d(TAG, "Registro completado -> $resumenPersonaje")
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}