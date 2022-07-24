package com.example.proyectominecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.proyectominecraft.Activity.AutoraActivity;
import com.example.proyectominecraft.Activity.EscenasActivity;
import com.example.proyectominecraft.Activity.PersonajesActivity;

/**
 *  Clase MainActivity
 *
 *  Muestra 3 botones para las Activitys Escenas, Personajes y Autora, utilizando la navegacion por ventanas.
 *  Se uso el objeto de mensajeria Intent para llamar a las otras Activitys.
 *  Se crea un metodo por cada Activity que llamaremos.
 *  Los botones fueron creados en el .XMl agregando los metodos en la funcion onClick.
 *  Las palabras dentro de cada boton fueron creadas y llamadas de recursos string.
 *  Se utilizaron atributos ImageView para cada textura como el fondo y las palabras.
 *  Ademas, se agrego el API MediaPlayer para reproducir sonido en toda la aplicacion.
 *
 * @author Domenica Vizcarra
 */

public class MainActivity extends AppCompatActivity {

    MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        music = MediaPlayer.create(this, R.raw.minecraft);
        music.start();

    }

    public void Escenas(View view){
        Intent escenas=new Intent(this, EscenasActivity.class);
        startActivity(escenas);
    }

    public void Personajes(View view){
        Intent personajes=new Intent(this, PersonajesActivity.class);
        startActivity(personajes);
    }

    public void Autora(View view){
        Intent autora=new Intent(this, AutoraActivity.class);
        startActivity(autora);
    }

}