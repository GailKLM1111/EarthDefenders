package com.gailgames.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Enemigo {
    public Vector2 posicion;
    public Sprite enemigo;
    public Boolean vivo = true;
    public int salud = 2;
    public Enemigo(Texture enemigoImg) {

//        posicion = posicionP;
        posicion = posicionRandom();
        enemigo = new Sprite(enemigoImg);
        enemigo.setScale(1);

    }

    public void Pintar(SpriteBatch batch) {
        enemigo.setPosition(posicion.x, posicion.y);
        enemigo.draw(batch);
    }

    public Vector2 posicionRandom() {
        int x = MathUtils.random(10, 1080-170);
        int y = MathUtils.random(700, 900);
        Vector2 posicion = new Vector2(x, y);
        return posicion;
    }

}
