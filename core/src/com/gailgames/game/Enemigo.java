package com.gailgames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Enemigo {
    public Vector2 posicion;
    public Sprite enemigo;
    public Boolean vivo = true;
    public int salud = 2;
    public Enemigo(Vector2 posicionP, Texture enemigoImg) {

        posicion = posicionP;
        enemigo = new Sprite(enemigoImg);
        enemigo.setScale(1);

    }

    public void Pintar(SpriteBatch batch) {
        enemigo.setPosition(posicion.x, posicion.y);
        enemigo.draw(batch);
    }

    public Vector2 posicionRandom() {
        int x = (int) (Math.random() * 1080);
        int y = (int) (Math.random() * 1920);
        Vector2 posicion = new Vector2(x, y);
        return posicion;
    }

}
