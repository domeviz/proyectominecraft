package com.example.proyectominecraft.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyectominecraft.R;

/**
 *  Clase EscenasActivity
 *
 *  Muestra 2 botones para cada escena utilizando la navegacion por ventanas.
 *  Se uso el objeto de mensajeria Intent para llamar a las otras Activitys.
 *  Se crea un metodo por cada Activity que llamaremos.
 *  Los botones fueron creados en el .XMl agregando los metodos en la funcion onClick.
 *  Las palabras dentro de cada boton fueron creadas y llamadas de recursos string.
 *  Ademas, se utilizo un atributo ImageView para el fondo.
 *
 * @author Domenica Vizcarra
 */

public class EscenasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escenas);
    }

    public void EscenaDiaActivity(View view){
        Intent dia=new Intent(this, EscenaDiaActivity.class);
        startActivity(dia);
    }

    public void EscenaNocheActivity(View view){
        Intent noche=new Intent(this,EscenaNocheActivity.class);
        startActivity(noche);
    }
}