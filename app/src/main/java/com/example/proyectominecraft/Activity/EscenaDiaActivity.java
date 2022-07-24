package com.example.proyectominecraft.Activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.example.proyectominecraft.R;
import com.example.proyectominecraft.Renders.RenderEscenaDia;

/**
 *  Clase EscenaDiaActivity
 *
 *  Muestra la escena de dia creada en RenderEscenaDia con la figura Cubo.
 *
 * @author Domenica Vizcarra
 */

public class EscenaDiaActivity extends Activity {

    private GLSurfaceView superficie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escena_dia);
        superficie = new GLSurfaceView(this);
        superficie.setEGLContextClientVersion(1);
        superficie.setRenderer(new RenderEscenaDia(this));
        setContentView(superficie);
    }

}