package com.gailgames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Lazer {

    Vector2 posicion;

    Sprite lazer;

    float velocidad = 3000;

    boolean vivo;

    public Lazer(Sprite lazerImg) {

        lazer = new Sprite(lazerImg);
        posicion = new Vector2(0, 1000000);
        vivo = false;

    }

    public void Mover(float deltaTime) {

        posicion.y += deltaTime * velocidad;

        if (posicion.y > 1920)
            Desaparecer();
        else
            vivo = true;

    }

    public void Disparar(Vector2 posicionNave) {

//        posicion.x = posicionNave.x + lazer.getWidth() / 2;
//        posicion.y = posicionNave.y + lazer.getHeight() / 2;

        posicion.x = posicionNave.x;
        posicion.y = posicionNave.y;

        vivo = true;

    }

    public void Desaparecer() {

        posicion.y = 1000000;
        vivo = false;

    }

    public void Pintar(SpriteBatch batch) {

        Mover(Gdx.graphics.getDeltaTime());
        lazer.setPosition(posicion.x, posicion.y);
        lazer.draw(batch);

    }

}
