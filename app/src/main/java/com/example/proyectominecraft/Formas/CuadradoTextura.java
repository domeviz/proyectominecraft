package com.example.proyectominecraft.Formas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 *
 * Clase CuadradoTextura
 *
 * Define los vertices, indices y texturas que usará para crear el fondo en el render.
 * Inicializamos buffers para los vertices, indices y texturas.
 *
 * @author Domenica Vizcarra
 */

public class CuadradoTextura {

    private FloatBuffer bufferVertices;
    private ByteBuffer bufferIndices;

    private int[] listTextura=new int[1];
    public FloatBuffer bufferTextura;

    public int crearTextura(GL10 gl10, Context contexto, int recurso){
        BitmapFactory.Options op=new BitmapFactory.Options();
        op.inScaled=false;
        Bitmap imagen=BitmapFactory.decodeResource(contexto.getResources(),recurso);

        gl10.glGenTextures(1, listTextura,0);
        gl10.glBindTexture(GL10.GL_TEXTURE_2D, listTextura[0]);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, imagen, 0);
        gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        imagen.recycle();
        return recurso;
    }

    public CuadradoTextura(){

        float vertices[]={
                -1.0f, -1.0f, //0
                1.0f, -1.0f,  //1
                -1.0f, 1.0f,  //2
                1.0f, 1.0f    //3
        };

        float[] coordTextura= {
                0.0f, 0.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f
        };

        byte indices[]={
                0, 3, 1,
                0, 2, 3
        };

        ByteBuffer vbb= ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());
        bufferVertices=vbb.asFloatBuffer();
        bufferVertices.put(vertices);
        bufferVertices.position(0);

        bufferIndices=ByteBuffer.allocateDirect(indices.length);
        bufferIndices.put(indices);
        bufferIndices.position(0);

        ByteBuffer bbText= ByteBuffer.allocateDirect(coordTextura.length*4);
        bbText.order(ByteOrder.nativeOrder());
        bufferTextura=bbText.asFloatBuffer();
        bufferTextura.put(coordTextura);
        bufferTextura.position(0);
    }


    public void dibujar(GL10 gl){
        gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(2,GL10.GL_FLOAT,0,bufferVertices);

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, listTextura[0]);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, bufferTextura);

        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glDrawElements(GL10.GL_TRIANGLES,6, GL10.GL_UNSIGNED_BYTE,bufferIndices);

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glFrontFace(GL10.GL_CCW);
    }
}
