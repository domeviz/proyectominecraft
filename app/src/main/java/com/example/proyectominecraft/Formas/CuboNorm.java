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
 * Clase CuboNorm
 *
 * Define los vertices, normales, caras y texturas que se usara para crear las figuras en el render.
 * Inicializamos buffers para los vertices, normales y texturas.
 * Este cubo, la tener normales podrá recibir la iluminacion.
 *
 * @author Domenica Vizcarra
 */
public class CuboNorm {

    private final FloatBuffer vertexBuffer;
    private final FloatBuffer normalBuffer;
    private final int numCaras = 6;
    public int[] ImagenID = new int[numCaras];

    private FloatBuffer texturaBuffer;

    private final int[] texturaID = new int[numCaras];
    private final Bitmap[] bitmap = new Bitmap[numCaras];
    private final Context context;

    public CuboNorm(Context context) {

        this.context = context;

        float[] vertices = {
                // Vertices de las 6 caras

                // Frente
                -1.0f, -1.0f, 1.0f,  // 0. izquierda-abajo-frente
                1.0f, -1.0f, 1.0f,  // 1. derecha-abajo-frente
                -1.0f, 1.0f, 1.0f,  // 2. izquierda-arriba-frente
                1.0f, 1.0f, 1.0f,  // 3. derecha-arriba-frente
                // Atras
                1.0f, -1.0f, -1.0f,  // 6. derecha-abajo-atras
                -1.0f, -1.0f, -1.0f,  // 4. izquierda-abajo-atras
                1.0f, 1.0f, -1.0f,  // 7. derecha-arriba-atras
                -1.0f, 1.0f, -1.0f,  // 5. izquierda-arriba-atras
                // Izquierda
                -1.0f, -1.0f, -1.0f,  // 4. izquierda-abajo-atras
                -1.0f, -1.0f, 1.0f,  // 0. izquierda-abajo-frente
                -1.0f, 1.0f, -1.0f,  // 5. izquierda-arriba-atras
                -1.0f, 1.0f, 1.0f,  // 2. izquierda-arriba-frente
                // Derecha
                1.0f, -1.0f, 1.0f,  // 1. derecha-abajo-frente
                1.0f, -1.0f, -1.0f,  // 6. derecha-abajo-atras
                1.0f, 1.0f, 1.0f,  // 3. derecha-arriba-frente
                1.0f, 1.0f, -1.0f,  // 7. derecha-arriba-atras
                // Arriba
                -1.0f, 1.0f, 1.0f,  // 2. izquierda-arriba-frente
                1.0f, 1.0f, 1.0f,  // 3. derecha-arriba-frente
                -1.0f, 1.0f, -1.0f,  // 5. izquierda-arriba-atras
                1.0f, 1.0f, -1.0f,  // 7. derecha-arriba-atras
                // Abajo
                -1.0f, -1.0f, -1.0f,  // 4. izquierda-abajo-atras
                1.0f, -1.0f, -1.0f,  // 6. derecha-abajo-atras
                -1.0f, -1.0f, 1.0f,  // 0. izquierda-abajo-frente
                1.0f, -1.0f, 1.0f   // 1. derecha-abajo-frente
        };

        float []normales={

                //Normales de cada cara

                -0.577f, -0.577f, 0.577f,
                0.577f, -0.577f, 0.577f,
                -0.577f, 0.577f, 0.577f,
                0.577f, 0.577f, 0.577f,

                0.577f, -0.577f, -0.577f,
                -0.577f, -0.577f, -0.577f,
                0.577f, 0.577f, -0.577f,
                -0.577f, 0.577f, -0.577f,

                -0.577f, -0.577f, -0.577f,
                -0.577f, -0.577f, 0.577f,
                -0.577f, 0.577f, -0.577f,
                -0.577f, 0.577f, 0.577f,

                0.577f, -0.577f, 0.577f,
                0.577f, -0.577f, -0.577f,
                0.577f, 0.577f, 0.577f,
                0.577f, 0.577f, -0.577f,

                - 0.577f, 0.577f, 0.577f,
                0.577f, 0.577f, 0.577f,
                -0.577f, 0.577f, -0.577f,
                0.577f, 0.577f, -0.577f

                - 0.577f, -0.577f, -0.577f,
                0.577f, -0.577f, -0.577f,
                -0.577f, -0.577f, 0.577f,
                0.577f, -0.577f, 0.577f

        };

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        float[] textureCoordinates = {0.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f};
        setTextureCoordinates(textureCoordinates);

        normalBuffer=creaFloatBuffer(normales);
    }

    /**
     * Método Dibujar
     *
     * Dibujamos cada cara del cubo con DrawArrays y la funcon TRIANGLE_STRIP
     * Además, en cada cara llamamos a la textura que se guardará en el vector ImagenID
     *
     */

    public void dibujar(GL10 gl) {

        gl.glFrontFace(GL10.GL_CCW);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        gl.glNormalPointer(GL10.GL_FLOAT,0,normalBuffer);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texturaBuffer);

        // Frente
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texturaID[0]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NICEST);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        // Izquierda
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texturaID[1]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NICEST);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);

        // Atras
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texturaID[2]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NICEST);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);

        // Derecha
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texturaID[3]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NICEST);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

        // Arriba
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texturaID[4]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NICEST);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);

        // Bottom.
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texturaID[5]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NICEST);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

    private void setTextureCoordinates(float[] textureCoords) {
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(textureCoords.length * 4 * 6);
        byteBuf.order(ByteOrder.nativeOrder());
        texturaBuffer = byteBuf.asFloatBuffer();
        for (int cara = 0; cara < numCaras; cara++)
            texturaBuffer.put(textureCoords);
        texturaBuffer.position(0);
    }

    public static FloatBuffer creaFloatBuffer(float[] matriz) {
        ByteBuffer bb = ByteBuffer.allocateDirect(matriz.length*4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(matriz);
        fb.position(0);
        return fb;
    }

    public void cargarTextura(GL10 gl) {

        gl.glGenTextures(6, texturaID, 0);

        for (int cara = 0; cara < numCaras; cara++) {

            bitmap[cara] = BitmapFactory.decodeStream(context.getResources().openRawResource(ImagenID[cara]));

            gl.glBindTexture(GL10.GL_TEXTURE_2D, texturaID[cara]);
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D,0, bitmap[cara],0);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);

            bitmap[cara].recycle();
        }
    }
}