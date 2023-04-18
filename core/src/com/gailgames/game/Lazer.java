package com.gailgames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Lazer {

    Vector2 posicion;

    Sprite lazer;

    float velocidad = 1000;

    public Lazer(Sprite lazerImg) {

        lazer = new Sprite(lazerImg);
        posicion = new Vector2(0, 1000000);

    }

    public void Mover(float deltaTime) {

        posicion.y += deltaTime * velocidad;

    }

    public void Disparar(Vector2 posicionNave) {

//        posicion.x = posicionNave.x + lazer.getWidth() / 2;
        posicion.x = 300;
        posicion.y = posicionNave.y + lazer.getHeight() / 2;

    }

    public void Pintar(SpriteBatch batch) {

        Mover(Gdx.graphics.getDeltaTime());
        lazer.setPosition(posicion.x, posicion.y);
        lazer.draw(batch);

    }

}
