package com.example.proyectominecraft.Activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.proyectominecraft.R;
import com.example.proyectominecraft.Renders.RenderEscenaNoche;

/**
 *  Clase EscenaDiaActivity
 *
 *  Muestra la escena en la noche creada en RenderEscenaNoche con la figura CuboNorm.
 *
 * @author Domenica Vizcarra
 */

public class EscenaNocheActivity extends Activity {

    private GLSurfaceView superficie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escena_noche);
        superficie = new GLSurfaceView(this);
        superficie.setEGLContextClientVersion(1);
        superficie.setRenderer(new RenderEscenaNoche(this));
        setContentView(superficie);
    }
}