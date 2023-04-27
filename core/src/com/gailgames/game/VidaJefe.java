package com.gailgames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VidaJefe {

    public int vida;
    public String stringVida;
    public BitmapFont fuente;

    public VidaJefe() {

        vida = 100;
        stringVida = "Vida: " + vida;
        fuente = new BitmapFont();
        fuente.setColor(Color.RED);
        fuente.getData().setScale(2, 2);

    }

    public void RestarVida(int puntos) {

        vida -= puntos;

    }

    public void Pintar(SpriteBatch batch) {

        stringVida = "Vida: " + vida;
        fuente.draw(batch, stringVida, Gdx.graphics.getWidth()/2 - 50, 100);

    }

}
