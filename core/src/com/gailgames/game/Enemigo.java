package com.gailgames.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class Enemigo {
    public Vector2 posicion;
    public Sprite enemigo;
    public Boolean vivo = true;
    public Sprite lazer;
    public LazerEnemigo lazerEnemigo;
    long tiempoDisparo;

    public int salud = 2;
    public Enemigo(Texture enemigoImg, Texture lazerEnemigoImg) {

        posicion = posicionRandom();
        enemigo = new Sprite(enemigoImg);
        enemigo.setScale(1);
        lazer = new Sprite(lazerEnemigoImg);
        lazerEnemigo = new LazerEnemigo(lazer);

    }

    public void Pintar(SpriteBatch batch) {
        lazerEnemigo.Pintar(batch);
        Atacar();
        enemigo.setPosition(posicion.x, posicion.y);
        enemigo.draw(batch);
    }

    public Vector2 posicionRandom() {
        int x = MathUtils.random(10, 1080-170);
        int y = MathUtils.random(700, 900);
        Vector2 posicion = new Vector2(x, y);
        return posicion;
    }

    public void Atacar() {

        int probabilidad = MathUtils.random(1, 100);

        if (probabilidad == 1 && vivo == true && lazerEnemigo.vivo == false && TimeUtils.timeSinceMillis(tiempoDisparo) > 2000) {

//            if (!lazerEnemigo.vivo && TimeUtils.timeSinceMillis(tiempoDisparo) > 1000) {
//                lazerEnemigo.Disparar(posicion);
//                tiempoDisparo = TimeUtils.millis();
//            }

            tiempoDisparo = TimeUtils.millis();
            lazerEnemigo.Disparar(posicion);

        }


    }

}
