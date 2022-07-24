package com.example.proyectominecraft.Renders;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.example.proyectominecraft.Formas.CuadradoTextura;
import com.example.proyectominecraft.Formas.CuboNorm;
import com.example.proyectominecraft.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 *  Clase RenderEscenaNoche
 *
 *  Muestra la escena en la noche con iluminacion en EscenaNocheActivity.
 *
 * @author Domenica Vizcarra
 */

public class RenderEscenaNoche implements GLSurfaceView.Renderer {

    private final CuboNorm cesped;
    private final CuboNorm tierra;
    private final CuboNorm antorcha;

    private final CuboNorm piedra;
    private final CuboNorm cabeza_humano;
    private final CuboNorm tronco_humano;
    private final CuboNorm brazo_humano;
    private final CuboNorm pies_humano;
    private final CuboNorm pies_zombie;
    private final CuboNorm cabeza_zombie;
    private final CuboNorm tronco_zombie;
    private final CuboNorm brazo_zombie;
    private final CuboNorm cabezacreeper;
    private final CuboNorm cuerpocreeper;
    private float vAngulo;
    private float var_x_suelo;
    private float var_x_humano;
    private float var_x_zombie;
    private float mTransY;
    private float mTransX;
    public final static int V_LUZSOLAR = GL10.GL_LIGHT0;
    public final static int V_LUZSOLAR1 = GL10.GL_LIGHT1;
    public final static int V_LUZSOLAR2 = GL10.GL_LIGHT2;

    private CuadradoTextura fondofrente;
    private CuadradoTextura fondoizq;
    private CuadradoTextura fondoder;
    private CuadradoTextura fondoarrib;
    private CuadradoTextura fondoinf;
    private CuadradoTextura fondodetras;

    private Context contexto;

    public RenderEscenaNoche(Context context) {

        cesped = new CuboNorm(context);
        tierra = new CuboNorm(context);
        piedra = new CuboNorm(context);
        antorcha = new CuboNorm(context);

        cabeza_humano = new CuboNorm(context);
        tronco_humano = new CuboNorm(context);
        brazo_humano = new CuboNorm(context);
        pies_humano = new CuboNorm(context);

        pies_zombie = new CuboNorm(context);
        cabeza_zombie = new CuboNorm(context);
        tronco_zombie = new CuboNorm(context);
        brazo_zombie = new CuboNorm(context);

        cabezacreeper = new CuboNorm(context);
        cuerpocreeper = new CuboNorm(context);

        fondofrente = new CuadradoTextura();

        fondoizq = new CuadradoTextura();
        fondoder = new CuadradoTextura();
        fondoarrib = new CuadradoTextura();
        fondoinf = new CuadradoTextura();
        fondodetras = new CuadradoTextura();

        this.contexto = context;

    }

    private void iniciarGeometria(GL10 gl) {

        int idRecurso;
        idRecurso= R.drawable.nether;
        fondofrente.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.nether;
        fondoizq.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.nether;
        fondoder.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.nether;
        fondoarrib.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.lava;
        fondoinf.crearTextura(gl, this.contexto, idRecurso);

        idRecurso= R.drawable.nether;
        fondodetras.crearTextura(gl, this.contexto, idRecurso);

        antorcha.ImagenID[0] = R.drawable.antorcha;  //1 frontal
        antorcha.ImagenID[1] = R.drawable.antorcha;//4 trasera
        antorcha.ImagenID[2] = R.drawable.antorcha;//2 lateral derecha
        antorcha.ImagenID[3] = R.drawable.antorcha;//3 lateral izquierda
        antorcha.ImagenID[4] = R.drawable.antorcha;//5 superior
        antorcha.ImagenID[5] = R.drawable.antorcha;//6 inferior

        cesped.ImagenID[0] = R.drawable.cesped_lateral;  //1 frontal
        cesped.ImagenID[1] = R.drawable.cesped_lateral;//4 trasera
        cesped.ImagenID[2] = R.drawable.cesped_lateral;//2 lateral derecha
        cesped.ImagenID[3] = R.drawable.cesped_lateral;//3 lateral izquierda
        cesped.ImagenID[4] = R.drawable.cesped_superior;//5 superior
        cesped.ImagenID[5] = R.drawable.tierra;//6 inferior

        tierra.ImagenID[0] = R.drawable.tierra;  //1 frontal
        tierra.ImagenID[1] = R.drawable.tierra;//4 trasera
        tierra.ImagenID[2] = R.drawable.tierra;//2 lateral derecha
        tierra.ImagenID[3] = R.drawable.tierra;//3 lateral izquierda
        tierra.ImagenID[4] = R.drawable.tierra;//5 superior
        tierra.ImagenID[5] = R.drawable.tierra;//6 inferior

        piedra.ImagenID[0] = R.drawable.ladrillo;  //1 frontal
        piedra.ImagenID[1] = R.drawable.ladrillo;//4 trasera
        piedra.ImagenID[2] = R.drawable.ladrillo;//2 lateral derecha
        piedra.ImagenID[3] = R.drawable.ladrillo;//3 lateral izquierda
        piedra.ImagenID[4] = R.drawable.ladrillo;//5 superior
        piedra.ImagenID[5] = R.drawable.ladrillo;//6 inferior

        cabeza_humano.ImagenID[0] = R.drawable.humano_cara;  //1 frontal
        cabeza_humano.ImagenID[1] = R.drawable.humano_cara_trasera;//4 trasera
        cabeza_humano.ImagenID[2] = R.drawable.humano_cara_derecha;//2 lateral derecha
        cabeza_humano.ImagenID[3] = R.drawable.humano_cara_izquierda;//3 lateral izquierda
        cabeza_humano.ImagenID[4] = R.drawable.humano_cara_superior;//5 superior
        cabeza_humano.ImagenID[5] = R.drawable.humano_cara_inferior;//6 inferior

        tronco_humano.ImagenID[0] = R.drawable.humano_tronco;  //1 frontal
        tronco_humano.ImagenID[1] = R.drawable.humano_tronco_posterior;//4 trasera
        tronco_humano.ImagenID[2] = R.drawable.humano_cara_superior;//2 lateral derecha
        tronco_humano.ImagenID[3] = R.drawable.humano_cara_superior;//3 lateral izquierda
        tronco_humano.ImagenID[4] = R.drawable.humano_cara_superior;//5 superior
        tronco_humano.ImagenID[5] = R.drawable.humano_cara_superior;//6 inferior

        brazo_humano.ImagenID[0] = R.drawable.humano_brazo;  //1 frontal
        brazo_humano.ImagenID[1] = R.drawable.humano_brazo;//4 trasera
        brazo_humano.ImagenID[2] = R.drawable.humano_brazo;//2 lateral derecha
        brazo_humano.ImagenID[3] = R.drawable.humano_brazo;//3 lateral izquierda
        brazo_humano.ImagenID[4] = R.drawable.humano_brazo_superior;//5 superior
        brazo_humano.ImagenID[5] = R.drawable.humano_brazo_inferior;//6 inferior

        pies_humano.ImagenID[0] = R.drawable.humano_pies;  //1 frontal
        pies_humano.ImagenID[1] = R.drawable.humano_pies_posterior;//4 trasera
        pies_humano.ImagenID[2] = R.drawable.humano_pies_derecha;//2 lateral derecha
        pies_humano.ImagenID[3] = R.drawable.humano_pies_izquierda;//3 lateral izquierda
        pies_humano.ImagenID[4] = R.drawable.humano_cara_superior;//5 superior
        pies_humano.ImagenID[5] = R.drawable.humano_cara_superior;//6 inferior

        pies_zombie.ImagenID[0] = R.drawable.humano_pies;  //1 frontal
        pies_zombie.ImagenID[1] = R.drawable.humano_pies_posterior;//4 trasera
        pies_zombie.ImagenID[2] = R.drawable.humano_pies_derecha;//2 lateral derecha
        pies_zombie.ImagenID[3] = R.drawable.humano_pies_izquierda;//3 lateral izquierda
        pies_zombie.ImagenID[4] = R.drawable.humano_cara_superior;//5 superior
        pies_zombie.ImagenID[5] = R.drawable.humano_cara_superior;//6 inferior

        cabeza_zombie.ImagenID[0] = R.drawable.zombie_cara;  //1 frontal
        cabeza_zombie.ImagenID[1] = R.drawable.zombie_cara_trasera;//4 trasera
        cabeza_zombie.ImagenID[2] = R.drawable.zombie_cara_trasera;//2 lateral derecha
        cabeza_zombie.ImagenID[3] = R.drawable.zombie_cara_trasera;//3 lateral izquierda
        cabeza_zombie.ImagenID[4] = R.drawable.zombie_cara_trasera;//5 superior
        cabeza_zombie.ImagenID[5] = R.drawable.zombie_cara_trasera;//6 inferior

        tronco_zombie.ImagenID[0] = R.drawable.zombie_tronco;  //1 frontal
        tronco_zombie.ImagenID[1] = R.drawable.humano_tronco_posterior;//4 trasera
        tronco_zombie.ImagenID[2] = R.drawable.zombie_tronco;//2 lateral derecha
        tronco_zombie.ImagenID[3] = R.drawable.zombie_tronco;//3 lateral izquierda
        tronco_zombie.ImagenID[4] = R.drawable.zombie_cara_trasera;//5 superior
        tronco_zombie.ImagenID[5] = R.drawable.zombie_cara_trasera;//6 inferior

        brazo_zombie.ImagenID[0] = R.drawable.zombie_brazo;  //1 frontal
        brazo_zombie.ImagenID[1] = R.drawable.zombie_brazo;//4 trasera
        brazo_zombie.ImagenID[2] = R.drawable.zombie_brazo;//2 lateral derecha
        brazo_zombie.ImagenID[3] = R.drawable.zombie_brazo;//3 lateral izquierda
        brazo_zombie.ImagenID[4] = R.drawable.humano_brazo_superior;//5 superior
        brazo_zombie.ImagenID[5] = R.drawable.zombie_cara_trasera;//6 inferior

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
        iniciarIluminacion(gl);
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

        cesped.cargarTextura(gl);
        tierra.cargarTextura(gl);
        piedra.cargarTextura(gl);
        antorcha.cargarTextura(gl);

        cabeza_humano.cargarTextura(gl);
        tronco_humano.cargarTextura(gl);
        brazo_humano.cargarTextura(gl);
        pies_humano.cargarTextura(gl);

        pies_zombie.cargarTextura(gl);

        cabeza_zombie.cargarTextura(gl);
        tronco_zombie.cargarTextura(gl);
        brazo_zombie.cargarTextura(gl);

        cabezacreeper.cargarTextura(gl);
        cuerpocreeper.cargarTextura(gl);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        float radio;
        float zCercano = 1.0f;

        float zLejano = 400f;
        float campoVista = 100.0f/57.3f;
        float tamanio;

        gl.glEnable(GL10.GL_NORMALIZE);
        radio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        tamanio = zCercano * (float)(Math.tan((float)(campoVista/2.0f)));
        gl.glFrustumf(-tamanio, tamanio, -tamanio/radio, tamanio/radio, zCercano, zLejano);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }

    private void iniciarIluminacion(GL10 gl) {
        float[] posicionSol = {0, 1.5f, 0.0f, 1.0f};
        float[] pos1 = {-15.0f, 15.0f, 0.0f, 1.0f};
        float[] pos2 = {-10.0f, -4.0f, 0.0f, 1.0f};
        float[] blanco = {1.0f, 1.0f, 1.0f, 1.0f};
        float[] azul = {0.0f, 0.0f, .2f, 1.0f};
        float[] cyan = {0.0f, 1.0f, 1.0f, 1.0f};
        float[] amarillo = {1.0f, 1.0f, 0.0f, 1.0f};
        float[] dimcyan = {0.0f, .5f, .5f, 1.0f};

        //donde esta la camara primeros 3
        //donde ve la camara los otros 3
        gl.glLightfv(V_LUZSOLAR, GL10.GL_POSITION, CuboNorm.creaFloatBuffer(posicionSol));
        gl.glLightfv(V_LUZSOLAR, GL10.GL_DIFFUSE, CuboNorm.creaFloatBuffer(blanco));
        gl.glLightfv(V_LUZSOLAR, GL10.GL_EMISSION, CuboNorm.creaFloatBuffer(amarillo));

        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(V_LUZSOLAR);
        gl.glEnable(V_LUZSOLAR1);
        gl.glEnable(V_LUZSOLAR2);
    }



    @Override
    public void onDrawFrame(GL10 gl) {
        float[] posicionSol = {0, 1.5f, 0.0f, 1.0f};
        float[] pos1 = {-15.0f, 15.0f, 0.0f, 1.0f};
        float[] pos2 = {-10.0f, -4.0f, 0.0f, 1.0f};
        float[] blanco = {1.0f, 1.0f, 1.0f, 1.0f};
        float[] azul = {0.0f, 0.0f, .2f, 1.0f};
        float[] cyan = {0.0f, 1.0f, 1.0f, 1.0f};
        float[] amarillo = {1.0f, 1.0f, 0.0f, 1.0f};
        float[] dimcyan = {0.0f, .5f, .5f, 1.0f};
        float[] rojo={0.1f, 0.0f, 0.0f, 1.0f};
        float[] negro={0.0f, 0.0f, 0.0f, 1.0f};


        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        GLU.gluLookAt(gl, 0, 0, 7, 0, 0, 0, 0, 1.0f, 0.0f);

        gl.glPushMatrix();

        gl.glLightfv(V_LUZSOLAR, GL10.GL_POSITION,CuboNorm.creaFloatBuffer(posicionSol));

        //Fondo
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

        gl.glTranslatef(0f, 0.5f, -4f);
        gl.glTranslatef(-0.5f, -0.5f, -0.0f);


        //PIEDRA

        gl.glPushMatrix();
        gl.glRotatef(25,0,1,0);
        gl.glTranslatef(0.0f,-0.75f,1f);
        gl.glScalef(3.0f,0.25f,1.15f);
        piedra.dibujar(gl);
        gl.glPopMatrix();

        //Zombie 1

        gl.glPushMatrix();
        gl.glTranslatef(2.5f, 0.0f, 2.0f);
        gl.glRotatef(-70,0,1,0);
        Zombie(gl);
        gl.glPopMatrix();

        //Humano

        gl.glPushMatrix();
        gl.glTranslatef(0.5f,-2.2f,-0.5f);
        gl.glRotatef(-90,1,1,1);

        Humano(gl);
        gl.glPopMatrix();

        //CREEPER

        gl.glPushMatrix();
        gl.glTranslatef(-2.0f, 0f, 3.5f);
        Creeper(gl);
        gl.glPopMatrix();

        vAngulo += 1.0f;
        var_x_suelo = 0.5f;
        var_x_humano = 0.5f;
        var_x_zombie = 0.5f;
        mTransX += 0.04f;

        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_EMISSION, CuboNorm.creaFloatBuffer(rojo));

        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, CuboNorm.creaFloatBuffer(negro));
        Antorcha(gl);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_EMISSION, CuboNorm.creaFloatBuffer(rojo));

        Piso(gl);
        gl.glPopMatrix();

    }


    public void Antorcha(GL10 gl) {

        gl.glPushMatrix();

        gl.glTranslatef(0, 1.5f, 0.0f);
        gl.glScalef(0.25f, 2.5f, 0.5f);
        antorcha.dibujar(gl);
        gl.glPopMatrix();

    }

    public void Humano(GL10 gl){

        gl.glPushMatrix();
        gl.glScalef(0.75f, 0.75f, 0.75f);

        gl.glPushMatrix();
        gl.glTranslatef(-0.5f + var_x_humano, 1.3f, 0.0f);
        gl.glScalef(0.5f, 0.5f, 0.5f);
        cabeza_humano.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(-0.5f + var_x_humano, -0.0f, 0f);
        gl.glScalef(0.5f, 0.80f, 0.25f);
        tronco_humano.dibujar(gl);
        gl.glPopMatrix();

        //brazoderecho
        gl.glPushMatrix();
        gl.glTranslatef(-1.15f + var_x_humano, -0.0f, 0f);
        gl.glScalef(0.15f, 0.80f, 0.25f);
        brazo_humano.dibujar(gl);
        gl.glPopMatrix();

        //brazo izquierdo
        gl.glPushMatrix();
        gl.glTranslatef(0.15f + var_x_humano, -0.0f, 0f);
        gl.glScalef(0.15f, 0.80f, 0.25f);
        brazo_humano.dibujar(gl);
        gl.glPopMatrix();

        //pies humano
        gl.glPushMatrix();
        gl.glTranslatef(-0.5f + var_x_humano, -1.60f, 0f);
        gl.glScalef(0.5f, 0.80f, 0.25f);
        pies_humano.dibujar(gl);
        gl.glPopMatrix();

        gl.glPopMatrix();// Fin humano
    }

    public void Zombie(GL10 gl){

        gl.glPushMatrix();

        gl.glScalef(0.75f,0.75f,0.75f);

        gl.glPushMatrix();
        gl.glTranslatef(-0.5f+var_x_zombie,1.3f,0f);
        gl.glScalef(0.5f,0.5f,0.5f);
        cabeza_zombie.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(-0.5f+var_x_zombie,-0.0f,0f);
        gl.glScalef(0.5f,0.80f,0.25f);
        tronco_zombie.dibujar(gl);
        gl.glPopMatrix();

        //brazoderecho
        gl.glPushMatrix();
        mTransY += 0.08f;
        gl.glRotatef(-90, 1, 0, 0 );
        gl.glRotatef((float)Math.sin(mTransY), 1, 0, 0);
        gl.glTranslatef(-1.15f+var_x_zombie,-0.6f,0.4f);

        gl.glScalef(0.15f,0.80f,0.25f);
        brazo_zombie.dibujar(gl);
        gl.glPopMatrix();

        //brazo izquierdo
        gl.glPushMatrix();
        mTransY += 0.08f;
        gl.glRotatef(-90, 1, 0, 0 );
        gl.glRotatef((float)Math.sin(mTransY), 1, 0, 0);
        gl.glTranslatef(0.15f+var_x_zombie,-0.6f,0.4f);
        gl.glScalef(0.15f,0.80f,0.25f);
        brazo_zombie.dibujar(gl);
        gl.glPopMatrix();

        //pies zombie----usa los mismos del humano
        gl.glPushMatrix();
        gl.glTranslatef(-0.5f+var_x_zombie,-1.60f,0f);
        gl.glScalef(0.5f,0.80f,0.25f);
        pies_zombie.dibujar(gl);
        gl.glPopMatrix();

        gl.glPopMatrix();// Fin Zombie

    }

    public void Creeper(GL10 gl){

        gl.glPushMatrix();
        //gl.glRotatef(vAngulo, 0f, 1f, 0f);
        gl.glRotatef(90, 0f, 1f, 0f);
        //gl.glTranslatef(0.0f, 0.0f, 0f);
        gl.glScalef(0.75f,0.75f,0.75f);
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
    public void Piso(GL10 gl){

        gl.glRotatef(-35,0.05f,1f,0f);
        gl.glTranslatef(-4f,-4f,-4f);
        gl.glScalef(1.45f,1,1.25f);
        gl.glPushMatrix();

        for(float i=-12.0f;i<=4.0f;i++) {
            for (float j = -14.0f; j <= 4.0f; j++) {
                gl.glPushMatrix();
                gl.glTranslatef(i, 0.0f, j);
                gl.glScalef(0.5f, 0.5f, 0.5f);
                cesped.dibujar(gl);
                gl.glPopMatrix();
            }
        }

        gl.glPopMatrix();
    }



}




