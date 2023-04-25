package com.gailgames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Explocion {

    Animation < TextureRegion > animacion;
    public Vector2 posicion;

    public float estadoFrame;

    private final int FRAME_COLUMNAS = 10, FRAME_FILAS = 6;

    public int contadorFrames;

    public Explocion (Texture explosionSheet, Vector2 posicion) {

        this.posicion = new Vector2();
        Posicion(posicion);

        TextureRegion [][] temporal = TextureRegion.split(explosionSheet, explosionSheet.getWidth() / FRAME_COLUMNAS, explosionSheet.getHeight() / FRAME_FILAS);
        TextureRegion [] explosionframes = new TextureRegion[FRAME_COLUMNAS * FRAME_FILAS];

        int indice = 0;
        for (int i = 0; i < FRAME_FILAS; i++) {
            for (int j = 0; j < FRAME_COLUMNAS; j++) {
                explosionframes[indice++] = temporal[i][j];
            }
        }

//        animacion = new Animation < TextureRegion > (0.025f, explosionframes);
        animacion = new Animation < TextureRegion > (0.5f, explosionframes);
        estadoFrame = 0f;
        contadorFrames = 0;

    }

    public void Posicion(Vector2 posicion) {

        this.posicion.x = posicion.x;
        this.posicion.y = posicion.y;

    }

    public void Desaparecer() {

        Posicion(new Vector2(1000000, 1000000));

    }

    public void Pintar(SpriteBatch batch) {
        contadorFrames ++;

//        if (contadorFrames == 60) {
//            Desaparecer();
//            contadorFrames = 0;
//        }

        estadoFrame += Gdx.graphics.getDeltaTime();
        TextureRegion frameActual = animacion.getKeyFrame(estadoFrame, true);
        batch.draw(frameActual, posicion.x, posicion.y);

    }




}
