package com.gailgames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Vida {

    public boolean vivo;
    public Sprite vida;
    public Vector2 posicion;

    public Vida(Texture vidaImg) {

        vida = new Sprite(vidaImg);
        vivo = true;
        DefinirPosicion();

    }

    public void DefinirPosicion() {

        posicion.x = Gdx.graphics.getWidth() - 60;
        posicion.y = Gdx.graphics.getHeight() - 60;

    }

    public void DañoRecibido() {

        vivo = false;

    }

    public void Pintar(SpriteBatch batch) {

        vida.draw(batch);

    }


}
