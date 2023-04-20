package com.gailgames.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class EnemigoKamikaze {

    public Vector2 posicion;
    public Sprite enemigoKamikaze;
    public Boolean vivo = true;
    public Sprite lazer;
    long ultimoKmikaze;
    public int salud = 2;
    int velocidad = 1;
    public EnemigoKamikaze(Texture enemigoImg) {

        posicion = posicionRandom();
        enemigoKamikaze = new Sprite(enemigoImg);
        enemigoKamikaze.setScale(1);

    }

    public void Pintar(SpriteBatch batch, Vector2 posicionNaveJugador) {
        Atacar(posicionNaveJugador);
        enemigoKamikaze.setPosition(posicion.x, posicion.y);
        enemigoKamikaze.draw(batch);
    }

    public Vector2 posicionRandom() {
        int x = MathUtils.random(0, 1080-170);
        int y = MathUtils.random(500, 600);
        Vector2 posicion = new Vector2(x, y);
        return posicion;
    }

    public void Atacar(Vector2 posicionNaveJugador) {

        int probabilidad = MathUtils.random(1, 100);

        if (probabilidad <= 90 && vivo) {

            ultimoKmikaze = TimeUtils.millis();
            KamiKaze(posicionNaveJugador);

        }


    }

    public void KamiKaze(Vector2 posicionNaveJugador) {

        if (posicion.x < posicionNaveJugador.x) {
            posicion.x += velocidad;
        } else {
            posicion.x -= velocidad;
        }
        if (posicion.y < posicionNaveJugador.y) {
            posicion.y += velocidad;
        } else {
            posicion.y -= velocidad;
        }

        if (posicion.y == posicionNaveJugador.y) {
            vivo = false;
        }

    }

}
