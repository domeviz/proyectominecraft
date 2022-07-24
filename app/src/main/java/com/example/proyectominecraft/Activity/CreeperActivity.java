package com.example.proyectominecraft.Activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.example.proyectominecraft.R;
import com.example.proyectominecraft.Renders.RenderCreeper;

/**
 *  Clase CreeperActivity
 *
 *  Muestra la figura del Creeper creada en el RenderCreeper con la figura Cubo.
 *
 * @author Domenica Vizcarra
 */

public class CreeperActivity extends Activity {

    private GLSurfaceView superficie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creeper);
        superficie = new GLSurfaceView(this);
        superficie.setEGLContextClientVersion(1);
        superficie.setRenderer(new RenderCreeper(this));
        setContentView(superficie);
    }

}