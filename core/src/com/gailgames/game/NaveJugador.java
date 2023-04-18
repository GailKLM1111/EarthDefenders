package com.gailgames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class NaveJugador {

    public Vector2 posicion;
    public Vector2 posicionLazer;
    public Sprite naveJugador;
    public Sprite lazer;
    public float velocidad = 500;
    public float velocidadLazer = 1000;

    public NaveJugador(Texture naveJugadorImg, Texture lazerImg) {

        naveJugador = new Sprite(naveJugadorImg);
        lazer = new Sprite(lazerImg);
        naveJugador.setScale(1);
        posicion = new Vector2(Gdx.graphics.getWidth() / 2, naveJugador.getScaleY() * naveJugador.getHeight() / 2);
        posicionLazer = new Vector2(0, 1000000);

    }

    public void Mover(float deltaTime) {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && posicionLazer.y > 1920) {
            posicionLazer.x = posicion.x + naveJugador.getWidth() / 2 - lazer.getWidth() / 2;
            posicionLazer.y = posicion.y + naveJugador.getHeight() / 2 + lazer.getHeight() / 2;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) posicion.x -= deltaTime * velocidad;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) posicion.x += deltaTime * velocidad;

        if (posicion.x < 0) posicion.x = 0;
        if (posicion.x > 1080 - 160) posicion.x = 1080 - 160;

        posicionLazer.y += deltaTime * velocidadLazer;

    }

    public void Pintar(SpriteBatch batch) {
        Mover(Gdx.graphics.getDeltaTime());
        naveJugador.setPosition(posicion.x, posicion.y);
        naveJugador.draw(batch);
        lazer.setPosition(posicionLazer.x, posicionLazer.y);
        lazer.draw(batch);
    }

}
