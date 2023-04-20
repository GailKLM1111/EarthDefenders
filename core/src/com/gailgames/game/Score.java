package com.gailgames.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Score {

    public int score;

    public String scoreString;

    public BitmapFont fuente;

    public Score () {

        score = 0;
        scoreString = "Score: " + score;
        fuente = new BitmapFont();
        fuente.setColor(1, 1, 1, 1);
        fuente.getData().setScale(2, 2);

    }

    public void SumarPuntos(int puntos) {

        score += puntos;

    }

    public void Pintar(SpriteBatch batch) {

        scoreString = "Score: " + score;
        fuente.draw(batch, scoreString, 10, 1920 - 10);

    }


}
