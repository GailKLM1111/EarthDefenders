package com.gailgames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class NaveJugador {

    public Vector2 posicion;
    public Vector2 posicionLazer;
    public Sprite naveJugador;
    public Sprite lazer;
    public float velocidad = 500;
    public float velocidadLazer = 1000;
    long tiempoDisparo1;
    long tiempoDisparo2;
    Lazer lazer1;
    Lazer lazer2;

    public NaveJugador(Texture naveJugadorImg, Texture lazerImg) {

        naveJugador = new Sprite(naveJugadorImg);
        lazer = new Sprite(lazerImg);
        lazer1 = new Lazer(lazer);
        lazer2 = new Lazer(lazer);
        naveJugador.setScale(1);
        posicion = new Vector2(Gdx.graphics.getWidth() / 2, naveJugador.getScaleY() * naveJugador.getHeight() / 2);
        posicionLazer = new Vector2(0, 1000000);

    }

    public void Mover(float deltaTime) {

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {

            if (!lazer1.vivo && TimeUtils.timeSinceMillis(tiempoDisparo1) > 1000) {
                lazer1.Disparar(CalculaPosicionLazer(1));
                tiempoDisparo1 = TimeUtils.millis();
            }

        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

            if (!lazer2.vivo && TimeUtils.timeSinceMillis(tiempoDisparo2) > 1000) {
                lazer2.Disparar(CalculaPosicionLazer(2));
                tiempoDisparo2 = TimeUtils.millis();
            }

        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) posicion.x -= deltaTime * velocidad;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) posicion.x += deltaTime * velocidad;

        if (posicion.x < 0) posicion.x = 0;
        if (posicion.x > 1080 - 160) posicion.x = 1080 - 160;

    }

    public Vector2 CalculaPosicionLazer (int numLazer) {

        Vector2 posicionLazer;

        if (numLazer == 1) {

            posicionLazer = new Vector2(posicion.x + lazer.getWidth() / 2, posicion.y + lazer.getHeight() / 2);

        } else {

            posicionLazer = new Vector2((posicion.x + lazer2.lazer.getWidth() / 2) + 150, posicion.y + lazer2.lazer.getHeight() / 2);

        }

        return posicionLazer;

    }

    public void Pintar(SpriteBatch batch) {
        Mover(Gdx.graphics.getDeltaTime());
        lazer1.Pintar(batch);
        lazer2.Pintar(batch);
        naveJugador.setPosition(posicion.x, posicion.y);
        naveJugador.draw(batch);
    }

}
