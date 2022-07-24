package com.example.proyectominecraft.Activity;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.os.Bundle;

import com.example.proyectominecraft.R;

/**
 *  Clase AutoresActivity
 *
 *  Se utilizaron atributos ImageView para cada textura como el fondo y las palabras.
 *
 * @author Domenica Vizcarra
 */

public class AutoraActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autora);
    }

}