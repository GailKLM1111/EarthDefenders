package com.gailgames.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {

    SpriteBatch batch;
    Texture naveJugadorImg;
    Texture lazerJugadorImg;
    Texture naveEnemigaImg;
    NaveJugador naveJugador;
    Enemigo[] enemigos;
    int columnas = 5;
    int filas = 5;

    @Override
    public void create() {

        batch = new SpriteBatch();
        naveJugadorImg = new Texture("naveJugador.png");
        lazerJugadorImg = new Texture("lazerJugador.png");
        naveEnemigaImg = new Texture("naveEnemiga.png");
        naveJugador = new NaveJugador(naveJugadorImg, lazerJugadorImg);
        enemigos = new Enemigo[columnas * filas];

        int i = 0;
        for (int y = 0; y < filas; y++) {
            for (int x = 0; x < columnas; x++) {
                Vector2 posicion = new Vector2(x * 200, y * 100);
                posicion.x += Gdx.graphics.getWidth() / 2;
                posicion.y += Gdx.graphics.getHeight();
                posicion.x -= (columnas/2) * 200;
                posicion.y -= (filas) * 100;
                enemigos[i] = new Enemigo(posicion, naveEnemigaImg);

                i++;
            }
        }

    }

    @Override
    public void render() {

        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        naveJugador.Pintar(batch);

        for (int i = 0; i < enemigos.length; i++) {

            if (enemigos[i].vivo) {

                if (naveJugador.lazer.getBoundingRectangle().overlaps(enemigos[i].enemigo.getBoundingRectangle())) {

                    naveJugador.posicionLazer.y = 10000;
                    enemigos[i].vivo = false;
                    break;

                }

            }
        }

        for (int i = 0; i < enemigos.length; i++) {

            if (enemigos[i].vivo) {
                enemigos[i].Pintar(batch);
            }

        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        naveJugadorImg.dispose();
    }

}
