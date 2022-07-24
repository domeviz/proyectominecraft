package com.example.proyectominecraft.Activity;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;

import com.example.proyectominecraft.R;

/**
 *  Clase PersonajesActivity
 *
 *  Muestra 3 botones para cada personaje utilizando la navegacion por ventanas.
 *  Se uso el objeto de mensajeria Intent para llamar a las otras Activitys.
 *  Se crea un metodo por cada Activity que llamaremos.
 *  Los botones fueron creados en el .XMl agregando los metodos en la funcion onClick.
 *  Las palabras dentro de cada boton fueron creadas y llamadas de recursos string.
 *  Ademas, se utilizo un atributo ImageView para el fondo.
 *
 * @author Domenica Vizcarra
 */

public class PersonajesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personajes);
    }

    public void Creeper(View view){
        Intent creeper=new Intent(this, CreeperActivity.class);
        startActivity(creeper);
    }

    public void Humano(View view){
        Intent humano=new Intent(this, HumanoActivity.class);
        startActivity(humano);
    }

    public void Zombie(View view){
        Intent zombie=new Intent(this, ZombieActivity.class);
        startActivity(zombie);
    }

}