package com.example.proyectominecraft.Activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.example.proyectominecraft.R;
import com.example.proyectominecraft.Renders.RenderHumano;

/**
 *  Clase HumanoActivity
 *
 *  Muestra la figura del Humano creada en el RenderHumano con la figura Cubo.
 *
 * @author Domenica Vizcarra
 */

public class HumanoActivity extends Activity {

    private GLSurfaceView superficie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humano);
        superficie = new GLSurfaceView(this);
        superficie.setEGLContextClientVersion(1);
        superficie.setRenderer(new RenderHumano(this));
        setContentView(superficie);
    }

}