package com.example.proyectominecraft.Renders;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.example.proyectominecraft.Formas.CuadradoTextura;
import com.example.proyectominecraft.Formas.Cubo;
import com.example.proyectominecraft.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 *  Clase RenderCreeper
 *
 *  Crea la figura del Creeper en el Creeper Activity
 *
 * @author Domenica Vizcarra
 */

public class RenderCreeper implements GLSurfaceView.Renderer {

    private final Cubo cabezacreeper;
    private final Cubo cuerpocreeper;

    private CuadradoTextura fondofrente,fondoizq,fondoder,fondoarrib,fondoinf,fondodetras;


    private float vAngulo;
    private float mTransY;

    private Context contexto;

    public RenderCreeper(Context context) {

        cabezacreeper = new Cubo(context);
        cuerpocreeper = new Cubo(context);

        fondofrente = new CuadradoTextura();
        fondoizq = new CuadradoTextura();
        fondoder = new CuadradoTextura();
        fondoarrib = new CuadradoTextura();
        fondoinf = new CuadradoTextura();
        fondodetras = new CuadradoTextura();

        this.contexto = context;
    }

    private void iniciarGeometria(GL10 gl){


        int idRecurso;
        idRecurso= R.drawable.fondo_creeper;
        fondofrente.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.fondo_creeper;
        fondoizq.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.fondo_creeper;
        fondoder.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.fondo_creeper;
        fondoarrib.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.fondo_creeper;
        fondoinf.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.fondo_creeper;
        fondodetras.crearTextura(gl, this.contexto, idRecurso);


        cuerpocreeper.ImagenID[0]= R.drawable.creeper_piel;
        cuerpocreeper.ImagenID[1]=R.drawable.creeper_piel;
        cuerpocreeper.ImagenID[2]=R.drawable.creeper_piel;
        cuerpocreeper.ImagenID[3]=R.drawable.creeper_piel;
        cuerpocreeper.ImagenID[4]=R.drawable.creeper_piel;
        cuerpocreeper.ImagenID[5]=R.drawable.creeper_piel;

        cabezacreeper.ImagenID[0]=R.drawable.creeper_piel;
        cabezacreeper.ImagenID[1]=R.drawable.creeper_piel;
        cabezacreeper.ImagenID[2]=R.drawable.creeper_cabeza;
        cabezacreeper.ImagenID[3]=R.drawable.creeper_piel;
        cabezacreeper.ImagenID[4]=R.drawable.creeper_piel;
        cabezacreeper.ImagenID[5]=R.drawable.creeper_piel;


    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        iniciarGeometria(gl);
        // Fondo negro
        gl.glClearColor(0f, 0f, 0f, 0f);
        // Profundidad en el valor más lejano
        gl.glClearDepthf(1.0f);
        // Habilitar el buffer de profundidad para eliminar superficies ocultas
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_BLEND);
        // Tipo de test de profundidad
        gl.glDepthFunc(GL10.GL_LEQUAL);
        // Vista en perspectiva
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        // Habilitar el sombreado suave de color
        gl.glShadeModel(GL10.GL_SMOOTH);
        // Seleccionar método de blending
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        // Permitir la transparencia
        gl.glEnable(GL10.GL_ALPHA_TEST);
        // Establece la función alfa
        gl.glAlphaFunc(GL10.GL_GREATER, 0.1f);
        // Habilitar la textura
        gl.glEnable(GL10.GL_TEXTURE_2D);
        // Cargar la textura de los cubos


        cabezacreeper.cargarTextura(gl);
        cuerpocreeper.cargarTextura(gl);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        float radio;
        float zCercano = 1.0f;

        float zLejano = 500f;
        float campoVista = 50.0f/57.3f;
        float tamanio;

        gl.glEnable(GL10.GL_NORMALIZE);
        radio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        tamanio = zCercano * (float)(Math.tan((float)(campoVista/2.0f)));
        gl.glFrustumf(-tamanio, tamanio, -tamanio/radio, tamanio/radio, zCercano, zLejano);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0f, 0.5f, -4f);

        Creeper(gl);

        vAngulo += 1.0f;
    }

    public void Creeper(GL10 gl){


        /**Escenario--Fondos*/
        gl.glPushMatrix();
        gl.glTranslatef(0, 0f, -100f);
        gl.glScalef(100,100,0);
        //gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_EMISSION, Planeta.creaFloatBuffer(negro));
        fondofrente.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0, 0f, 100f);
        gl.glRotatef(180,0,1,0);
        gl.glScalef(100,100,0);
        //gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_EMISSION, Planeta.creaFloatBuffer(negro));
        fondodetras.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(-100, 0, 0);
        gl.glRotatef(90,0,1,0);
        gl.glScalef(100,100,0);
        //gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_EMISSION, Planeta.creaFloatBuffer(negro));
        fondoizq.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(100, 0, 0);
        gl.glRotatef(-90,0,1,0);
        gl.glScalef(100,100,0);
        //gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_EMISSION, Planeta.creaFloatBuffer(negro));
        fondoder.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0, 100, 0);
        gl.glRotatef(-90,1,0,0);
        gl.glScalef(100,100,0);
        //gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_EMISSION, Planeta.creaFloatBuffer(negro));
        fondoarrib.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0, -100, 0);
        gl.glRotatef(90,1,0,0);
        gl.glScalef(100,100,0);
        //gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_EMISSION, Planeta.creaFloatBuffer(negro));
        fondoinf.dibujar(gl);
        gl.glPopMatrix();



        gl.glPushMatrix();
        gl.glRotatef(vAngulo, 0f, 1f, 0f);
        gl.glTranslatef(0.0f, 0.0f, 0f);
        //creeper
        gl.glPushMatrix();
        mTransY += 1f;
        gl.glRotatef((float)Math.sin(mTransY),1, 0, 0);
        gl.glTranslatef(0f, 1.3f, 0f );
        gl.glScalef(0.5f, 0.5f, 0.5f);
        cabezacreeper.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        mTransY += 1f;
        gl.glRotatef((float)Math.sin(mTransY),1, 0, 0);
        gl.glTranslatef(0f, -0.2f, 0f);
        gl.glScalef(0.4f, 1.0f, 0.25f);
        cuerpocreeper.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        mTransY += 1f;
        gl.glRotatef((float)Math.sin(mTransY),1, 0, 0);
        gl.glTranslatef(0.0f, -1.5f, 0.5f );
        gl.glScalef(0.5f, 0.3f, 0.25f);
        cuerpocreeper.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        mTransY += 1f;
        gl.glRotatef((float)Math.sin(mTransY),1, 0, 0);
        gl.glTranslatef(0.0f, -1.5f, -0.5f);
        gl.glScalef(0.5f, 0.3f, 0.25f);
        cuerpocreeper.dibujar(gl);
        gl.glPopMatrix();

        gl.glPopMatrix();// Fin creeper
    }
}
