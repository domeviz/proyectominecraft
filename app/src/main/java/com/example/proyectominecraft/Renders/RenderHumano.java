package com.example.proyectominecraft.Renders;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.example.proyectominecraft.Formas.CuadradoTextura;
import com.example.proyectominecraft.Formas.Cubo;
import com.example.proyectominecraft.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 *  Clase RenderHumano
 *
 *  Crea la figura del Humano en el Humano Activity
 *
 * @author Domenica Vizcarra
 */

public class RenderHumano implements GLSurfaceView.Renderer {

    private final Cubo cabeza_humano;
    private final Cubo tronco_humano;
    private final Cubo brazo_humano;
    private final Cubo pies_humano;

    private CuadradoTextura fondofrente,fondoizq,fondoder,fondoarrib,fondoinf,fondodetras;

    private float vAngulo;


    private Context contexto;

    public RenderHumano(Context context) {

        cabeza_humano = new Cubo(context);
        tronco_humano= new Cubo(context);
        brazo_humano= new Cubo(context);
        pies_humano= new Cubo(context);

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
        idRecurso= R.drawable.cielo;
        fondofrente.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.cielo;
        fondoizq.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.cielo;
        fondoder.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.cielo;
        fondoarrib.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.cielo;
        fondoinf.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.cielo;
        fondodetras.crearTextura(gl, this.contexto, idRecurso);

        cabeza_humano.ImagenID[0]= R.drawable.humano_cara;  //1 frontal
        cabeza_humano.ImagenID[1]=R.drawable.humano_cara_trasera;//4 trasera
        cabeza_humano.ImagenID[2]=R.drawable.humano_cara_derecha;//2 lateral derecha
        cabeza_humano.ImagenID[3]=R.drawable.humano_cara_izquierda;//3 lateral izquierda
        cabeza_humano.ImagenID[4]=R.drawable.humano_cara_superior;//5 superior
        cabeza_humano.ImagenID[5]=R.drawable.humano_cara_inferior;//6 inferior

        tronco_humano.ImagenID[0]=R.drawable.humano_tronco;  //1 frontal
        tronco_humano.ImagenID[1]=R.drawable.humano_tronco_posterior;//4 trasera
        tronco_humano.ImagenID[2]=R.drawable.humano_cara_superior;//2 lateral derecha
        tronco_humano.ImagenID[3]=R.drawable.humano_cara_superior;//3 lateral izquierda
        tronco_humano.ImagenID[4]=R.drawable.humano_cara_superior;//5 superior
        tronco_humano.ImagenID[5]=R.drawable.humano_cara_superior;//6 inferior

        brazo_humano.ImagenID[0]=R.drawable.humano_brazo;  //1 frontal
        brazo_humano.ImagenID[1]=R.drawable.humano_brazo;//4 trasera
        brazo_humano.ImagenID[2]=R.drawable.humano_brazo;//2 lateral derecha
        brazo_humano.ImagenID[3]=R.drawable.humano_brazo;//3 lateral izquierda
        brazo_humano.ImagenID[4]=R.drawable.humano_brazo_superior;//5 superior
        brazo_humano.ImagenID[5]=R.drawable.humano_brazo_inferior;//6 inferior

        pies_humano.ImagenID[0]=R.drawable.humano_pies;  //1 frontal
        pies_humano.ImagenID[1]=R.drawable.humano_pies_posterior;//4 trasera
        pies_humano.ImagenID[2]=R.drawable.humano_pies_derecha;//2 lateral derecha
        pies_humano.ImagenID[3]=R.drawable.humano_pies_izquierda;//3 lateral izquierda
        pies_humano.ImagenID[4]=R.drawable.humano_cara_superior;//5 superior
        pies_humano.ImagenID[5]=R.drawable.humano_cara_superior;//6 inferior

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

        cabeza_humano.cargarTextura(gl);
        tronco_humano.cargarTextura(gl);
        brazo_humano.cargarTextura(gl);
        pies_humano.cargarTextura(gl);


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        float radio;
        float zCercano = 1.0f;

        float zLejano = 500f;
        float campoVista = 70.0f/57.3f;
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

        Humano(gl);

        vAngulo += 1.0f;



    }

    public void Humano(GL10 gl){


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

        gl.glPushMatrix();
        gl.glTranslatef(-0.5f+0.5f, 1.3f, 0.0f);
        gl.glScalef(0.5f,0.5f,0.5f);
        cabeza_humano.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(-0.5f+0.5f, -0.0f, 0f);
        gl.glScalef(0.5f,0.80f,0.25f);
        tronco_humano.dibujar(gl);
        gl.glPopMatrix();

        //brazoderecho
        gl.glPushMatrix();
        gl.glTranslatef(-1.15f+0.5f, -0.0f, 0f);
        gl.glScalef(0.15f,0.80f,0.25f);
        brazo_humano.dibujar(gl);
        gl.glPopMatrix();

        //brazo izquierdo
        gl.glPushMatrix();
        gl.glTranslatef(0.15f+0.5f, -0.0f, 0f);
        gl.glScalef(0.15f,0.80f,0.25f);
        brazo_humano.dibujar(gl);
        gl.glPopMatrix();

        //pies humano
        gl.glPushMatrix();
        gl.glTranslatef(-0.5f+0.5f, -1.60f, 0f);
        gl.glScalef(0.5f,0.80f,0.25f);
        pies_humano.dibujar(gl);
        gl.glPopMatrix();


        gl.glPopMatrix();// Fin humano
    }
}
