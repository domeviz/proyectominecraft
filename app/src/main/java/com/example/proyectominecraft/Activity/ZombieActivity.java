package com.example.proyectominecraft.Activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.example.proyectominecraft.R;
import com.example.proyectominecraft.Renders.RenderZombie;

/**
 *  Clase ZombieActivity
 *
 *  Muestra la figura del Zombie creada en el RenderZombie con la figura Cubo.
 *
 * @author Domenica Vizcarra
 */
public class ZombieActivity extends Activity {

    private GLSurfaceView superficie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zombie);
        superficie = new GLSurfaceView(this);
        superficie.setEGLContextClientVersion(1);
        superficie.setRenderer(new RenderZombie(this));
        setContentView(superficie);
    }

}