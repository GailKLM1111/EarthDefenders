package com.gailgames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class LazerEnemigo {

    Vector2 posicion;

    Sprite lazerEnemigo;

    float velocidad = 500;

    boolean vivo;

    public LazerEnemigo(Sprite lazerImg) {

        lazerEnemigo = new Sprite(lazerImg);
//        lazerEnemigo.setColor(Color.FIREBRICK);
        lazerEnemigo.rotate(180);
        posicion = new Vector2(0, -1000000);
        vivo = false;

    }

    public void Mover(float deltaTime) {

        posicion.y -= deltaTime * velocidad;

        if (posicion.y < 0)
            Desaparecer();
        else
            vivo = true;

    }

    public void Disparar(Vector2 posicionEnemigo) {

        posicion.x = posicionEnemigo.x + lazerEnemigo.getWidth() / 2;
//        posicion.x = 300;
        posicion.y = posicionEnemigo.y + lazerEnemigo.getHeight() / 2;
        vivo = true;

    }

    public void Desaparecer() {

        posicion.y = -1000000;
        vivo = false;

    }

    public void Pintar(SpriteBatch batch) {

        Mover(Gdx.graphics.getDeltaTime());
        lazerEnemigo.setPosition(posicion.x, posicion.y);
        lazerEnemigo.draw(batch);

    }


}
