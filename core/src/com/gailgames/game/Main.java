package com.gailgames.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {

    SpriteBatch batch;
    Texture naveJugadorImg;
    Texture lazerJugadorImg;
    Texture naveEnemigaImg;
    NaveJugador naveJugador;
    Enemigo[] enemigos;
    int numEnemigos = 5;
    int nivel = 1;
    int oleada = 5;

    @Override
    public void create() {

        batch = new SpriteBatch();
        naveJugadorImg = new Texture("naveJugador.png");
        lazerJugadorImg = new Texture("lazerJugador.png");
        naveEnemigaImg = new Texture("naveEnemiga.png");
        naveJugador = new NaveJugador(naveJugadorImg, lazerJugadorImg);
        enemigos = new Enemigo[numEnemigos * nivel];

        for (int i = 0; i < enemigos.length; i++) {

            enemigos[i] = new Enemigo(naveEnemigaImg);

        }

    }

    @Override
    public void render() {

        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        naveJugador.Pintar(batch);

        for (int i = 0; i < enemigos.length; i++) {

            if (enemigos[i].vivo) {

//                if (naveJugador.lazer.getBoundingRectangle().overlaps(enemigos[i].enemigo.getBoundingRectangle())) {
//
//                    naveJugador.posicionLazer.y = 10000;
//                    enemigos[i].vivo = false;
//                    break;
//
//                }

                if (naveJugador.lazer1.lazer.getBoundingRectangle().overlaps(enemigos[i].enemigo.getBoundingRectangle())) {

                    naveJugador.lazer1.Desaparecer();
                    enemigos[i].vivo = false;
                    break;

                }

            }
        }

        int numEnemigosVivos = enemigos.length;
        int contador = 0;
        int numOleada = 1;

        for (int i = 0; i < enemigos.length; i++) {

            if (enemigos[i].vivo) {
                enemigos[i].Pintar(batch);
            } else {
                numEnemigosVivos--;
            }

        }

        if (numEnemigosVivos == 0) {

            nivel++;
            enemigos = new Enemigo[numEnemigos * nivel];

            for (int i = 0; i < enemigos.length; i++) {

                enemigos[i] = new Enemigo(naveEnemigaImg);

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
