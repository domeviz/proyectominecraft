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
 * Clase Cubo
 * 
 * Define los vertices, caras y texturas que se usará para crear las figuras en el render.
 * Inicializamos buffers para los vertices y texturas.
 *
 * @author Domenica Vizcarra
 */

public class Cubo {

    private final FloatBuffer vertexBuffer;
    private FloatBuffer texturaBuffer;

    /**
     * @param ImagenID Vector de enteros para las imagenes que llamaremos en el Render
     * @param texturaID Vector de enteros para las texturas de cada cara
     */

    private final int numCaras = 6;
    public int[] ImagenID = new int[numCaras];
    private final int[] texturaID = new int[numCaras];
    private final Bitmap[] bitmap = new Bitmap[numCaras];
    private final Context context;

    /**
     * Constructor que configura el bufer de vértices
     */
    public Cubo(Context context) {

        this.context = context;

        /**
         * @param vertices Vector de float para los vertices del cubo
         * @param texturaCoordenadas Vector de float para los vertices de la textura
         */

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

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        // Uso del orden de bytes nativo
        vbb.order(ByteOrder.nativeOrder());
        // Convertimos de byte a float
        vertexBuffer = vbb.asFloatBuffer();
        // Copiamos los datos en el buffer
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        float[] texturaCoordenadas = {
                0.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f
        };
        settexturaCoordenadas(texturaCoordenadas);
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

    /**
     * Método settexturaCoordenadas
     *
     * Establece las coordenadas de textura
     *
     */
    private void settexturaCoordenadas(float[] textureCoords) {
        // Como Float tiene 4 bytes, multiplicamos el número de vertices por 6 caras del cubo.
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(textureCoords.length * 4 * 6);
        byteBuf.order(ByteOrder.nativeOrder());
        texturaBuffer = byteBuf.asFloatBuffer();
        for (int cara = 0; cara < numCaras; cara++)
            texturaBuffer.put(textureCoords);
        texturaBuffer.position(0);
    }

    /**
     * Método cargarTextura
     *
     * Carga las imagenes para las 6 caras del cubo.
     *
     */

    public void cargarTextura(GL10 gl) {

        // Genera un array de texturasID para las imagenes.
        gl.glGenTextures(6, texturaID, 0);

        for (int cara = 0; cara < numCaras; cara++) {
            // Carga y voltea la textura
            bitmap[cara] = BitmapFactory.decodeStream(context.getResources().openRawResource(ImagenID[cara]));

            gl.glBindTexture(GL10.GL_TEXTURE_2D, texturaID[cara]);
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D,0, bitmap[cara],0);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
            bitmap[cara].recycle();
        }
    }
}