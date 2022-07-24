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
 *  Clase RenderZombie
 *
 *  Crea la figura del Zombie en el Zombie Activity
 *
 * @author Domenica Vizcarra
 */

public class RenderZombie implements GLSurfaceView.Renderer {

    private final Cubo pies_zombie;

    private final Cubo cabeza_zombie;
    private final Cubo tronco_zombie;
    private final Cubo brazo_zombie;

    private float vAngulo;
    private float var_x;
    private float var_x_zombie;

    private CuadradoTextura fondofrente,fondoizq,fondoder,fondoarrib,fondoinf,fondodetras;

    private float mTransY;

    private Context contexto;

    public RenderZombie(Context context) {

        pies_zombie = new Cubo(context);

        cabeza_zombie = new Cubo(context);
        tronco_zombie= new Cubo(context);
        brazo_zombie= new Cubo(context);

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
        idRecurso= R.drawable.fondo_zombie;
        fondofrente.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.fondo_zombie;
        fondoizq.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.fondo_zombie;
        fondoder.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.fondo_zombie;
        fondoarrib.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.fondo_zombie;
        fondoinf.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.fondo_zombie;
        fondodetras.crearTextura(gl, this.contexto, idRecurso);




        pies_zombie.ImagenID[0]= R.drawable.humano_pies;  //1 frontal
        pies_zombie.ImagenID[1]=R.drawable.humano_pies_posterior;//4 trasera
        pies_zombie.ImagenID[2]=R.drawable.humano_pies_derecha;//2 lateral derecha
        pies_zombie.ImagenID[3]=R.drawable.humano_pies_izquierda;//3 lateral izquierda
        pies_zombie.ImagenID[4]=R.drawable.humano_cara_superior;//5 superior
        pies_zombie.ImagenID[5]=R.drawable.humano_cara_superior;//6 inferior

        cabeza_zombie.ImagenID[0]=R.drawable.zombie_cara;  //1 frontal
        cabeza_zombie.ImagenID[1]=R.drawable.zombie_cara_trasera;//4 trasera
        cabeza_zombie.ImagenID[2]=R.drawable.zombie_cara_trasera;//2 lateral derecha
        cabeza_zombie.ImagenID[3]=R.drawable.zombie_cara_trasera;//3 lateral izquierda
        cabeza_zombie.ImagenID[4]=R.drawable.zombie_cara_trasera;//5 superior
        cabeza_zombie.ImagenID[5]=R.drawable.zombie_cara_trasera;//6 inferior

        tronco_zombie.ImagenID[0]=R.drawable.zombie_tronco;  //1 frontal
        tronco_zombie.ImagenID[1]=R.drawable.humano_tronco_posterior;//4 trasera
        tronco_zombie.ImagenID[2]=R.drawable.zombie_tronco;//2 lateral derecha
        tronco_zombie.ImagenID[3]=R.drawable.zombie_tronco;//3 lateral izquierda
        tronco_zombie.ImagenID[4]=R.drawable.zombie_cara_trasera;//5 superior
        tronco_zombie.ImagenID[5]=R.drawable.zombie_cara_trasera;//6 inferior

        brazo_zombie.ImagenID[0]=R.drawable.zombie_brazo;  //1 frontal
        brazo_zombie.ImagenID[1]=R.drawable.zombie_brazo;//4 trasera
        brazo_zombie.ImagenID[2]=R.drawable.zombie_brazo;//2 lateral derecha
        brazo_zombie.ImagenID[3]=R.drawable.zombie_brazo;//3 lateral izquierda
        brazo_zombie.ImagenID[4]=R.drawable.humano_brazo_superior;//5 superior
        brazo_zombie.ImagenID[5]=R.drawable.zombie_cara_trasera;//6 inferior

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

        pies_zombie.cargarTextura(gl);

        cabeza_zombie.cargarTextura(gl);
        tronco_zombie.cargarTextura(gl);
        brazo_zombie.cargarTextura(gl);

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

        Zombie(gl);

        vAngulo += 1.0f;
        var_x = 0.90f;
        var_x_zombie = 2.0f;

    }

    public void Zombie(GL10 gl){

        gl.glPushMatrix();
        gl.glTranslatef(0, 0f, -100f);
        gl.glScalef(100,100,0);
        fondofrente.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0, 0f, 100f);
        gl.glRotatef(180,0,1,0);
        gl.glScalef(100,100,0);
        fondodetras.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(-100, 0, 0);
        gl.glRotatef(90,0,1,0);
        gl.glScalef(100,100,0);
        fondoizq.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(100, 0, 0);
        gl.glRotatef(-90,0,1,0);
        gl.glScalef(100,100,0);
        fondoder.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0, 100, 0);
        gl.glRotatef(-90,1,0,0);
        gl.glScalef(100,100,0);
        fondoarrib.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0, -100, 0);
        gl.glRotatef(90,1,0,0);
        gl.glScalef(100,100,0);
        fondoinf.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glRotatef(vAngulo, 0f, 1f, 0f);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);

        gl.glPushMatrix();
        gl.glTranslatef(-0f, 1.3f, 0f);
        gl.glScalef(0.5f, 0.5f, 0.5f);
        cabeza_zombie.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(-0f, -0.0f, 0f);
        gl.glScalef(0.5f, 0.80f, 0.25f);
        tronco_zombie.dibujar(gl);
        gl.glPopMatrix();

        //brazoderecho
        gl.glPushMatrix();
        mTransY += 0.08f;
        gl.glRotatef(-90, 1, 0, 0 );
        gl.glRotatef((float)Math.sin(mTransY), 1, 0, 0);
        gl.glTranslatef(-0.65f, -0.6f, 0.4f);
        gl.glScalef(0.15f, 0.80f, 0.25f);

        brazo_zombie.dibujar(gl);
        gl.glPopMatrix();

        //brazo izquierdo
        gl.glPushMatrix();
        mTransY += 0.08f;
        gl.glRotatef(-90, 1, 0, 0 );
        gl.glRotatef((float)Math.sin(mTransY), 1, 0, 0);

        gl.glTranslatef(0.65f, -0.6f, 0.4f);
        gl.glScalef(0.15f, 0.80f, 0.25f);
        brazo_zombie.dibujar(gl);
        gl.glPopMatrix();

        //pies zombie----usa los mismos del humano
        gl.glPushMatrix();
        gl.glTranslatef(0f, -1.60f, 0f);
        gl.glScalef(0.5f, 0.80f, 0.25f);
        pies_zombie.dibujar(gl);
        gl.glPopMatrix();

        gl.glPopMatrix();// Fin Zombie
    }
}
