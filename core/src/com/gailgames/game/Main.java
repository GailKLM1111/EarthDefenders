package com.gailgames.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {

    SpriteBatch batch;
    Texture naveJugadorImg;
    Texture lazerJugadorImg;
    Texture lazerEnemigoImg;
    Texture naveEnemigaImg;
    Texture enemigoKamikazeImg;
    Texture explosionSheet;
    NaveJugador naveJugador;
    Enemigo[] enemigos;
    EnemigoKamikaze[] enemigosKamikaze;
    Score score;
    Explocion explosion;
    int numEnemigos = 5;
    int nivel = 1;
    int oleada = 5;

    Texture fondoImg;

    Sprite fondo;

    Vector2 posicionFondo;

    @Override
    public void create() {

        batch = new SpriteBatch();
        fondoImg = new Texture("fondo.png");
        naveJugadorImg = new Texture("naveJugador.png");
        lazerJugadorImg = new Texture("lazerJugador.png");
        lazerEnemigoImg = new Texture("lazerEnemigo.png");
        naveEnemigaImg = new Texture("naveEnemiga.png");
        enemigoKamikazeImg = new Texture("naveKamikaze.png");
        explosionSheet = new Texture("explosion_sheet.png");
        naveJugador = new NaveJugador(naveJugadorImg, lazerJugadorImg);
        enemigos = new Enemigo[numEnemigos * nivel];
        enemigosKamikaze = new EnemigoKamikaze[2];
        score = new Score();

        fondo = new Sprite(fondoImg);
        posicionFondo = new Vector2(0, 0);

        explosion = new Explocion(explosionSheet, new Vector2(-200, -2000));

        for (int i = 0; i < enemigos.length; i++) {

            enemigos[i] = new Enemigo(naveEnemigaImg, lazerEnemigoImg, explosionSheet);

        }

        for (int i = 0; i < enemigosKamikaze.length; i++) {

            enemigosKamikaze[i] = new EnemigoKamikaze(enemigoKamikazeImg);

        }

    }

    @Override
    public void render() {

        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();

        fondo.draw(batch);
        naveJugador.Pintar(batch);
        score.Pintar(batch);

//        if (explosion.contadorFrames == 60) {
//
//            explosion = new Explocion(explosionSheet, new Vector2(-200, -2000));
//
//        }

        for (int i = 0; i < enemigos.length; i++) {

            if (enemigos[i].vivo) {

                explosion.Pintar(batch);

                if (naveJugador.lazer1.lazer.getBoundingRectangle().overlaps(enemigos[i].enemigo.getBoundingRectangle())) {

                    explosion.Posicion(enemigos[i].posicion);

                    naveJugador.lazer1.Desaparecer();
                    enemigos[i].vivo = false;
                    score.SumarPuntos(10);
                    break;

                }

                if (naveJugador.lazer2.lazer.getBoundingRectangle().overlaps(enemigos[i].enemigo.getBoundingRectangle())) {

                    explosion.Posicion(enemigos[i].posicion);

                    naveJugador.lazer2.Desaparecer();
                    enemigos[i].vivo = false;
                    score.SumarPuntos(10);
                    break;

                }

            }

            if (enemigos[i].lazerEnemigo.lazerEnemigo.getBoundingRectangle().overlaps(naveJugador.naveJugador.getBoundingRectangle())) {

                score.SumarPuntos(-50);
                enemigos[i].lazerEnemigo.Desaparecer();

            }

        }

        for (int i = 0; i < enemigosKamikaze.length; i++) {

            if (enemigosKamikaze[i].vivo) {

                if (naveJugador.lazer1.lazer.getBoundingRectangle().overlaps(enemigosKamikaze[i].enemigoKamikaze.getBoundingRectangle())) {

                    naveJugador.lazer1.Desaparecer();
                    enemigosKamikaze[i].vivo = false;
                    score.SumarPuntos(50);
                    break;

                }

                if (naveJugador.lazer2.lazer.getBoundingRectangle().overlaps(enemigosKamikaze[i].enemigoKamikaze.getBoundingRectangle())) {

                    naveJugador.lazer2.Desaparecer();
                    enemigosKamikaze[i].vivo = false;
                    score.SumarPuntos(50);
                    break;

                }

                if (enemigosKamikaze[i].enemigoKamikaze.getBoundingRectangle().overlaps(naveJugador.naveJugador.getBoundingRectangle())) {

                    score.SumarPuntos(-50);
                    enemigosKamikaze[i].vivo = false;
                    break;

                }

            }
        }

        int numEnemigosVivos = enemigos.length;
        int contador = 0;
        int numOleada = 1;

        for (int i = 0; i < enemigos.length; i++) {

            enemigos[i].lazerEnemigo.Pintar(batch);

            if (enemigos[i].vivo) {
                enemigos[i].Pintar(batch);
            } else {
                numEnemigosVivos--;
            }

        }



        for (int i = 0; i < enemigosKamikaze.length; i++) {

            if (enemigosKamikaze[i].vivo) {
                enemigosKamikaze[i].Pintar(batch, naveJugador.posicion);
            }

        }

        if (numEnemigosVivos == 0) {

            nivel++;
            enemigos = new Enemigo[numEnemigos * nivel];

            for (int i = 0; i < enemigos.length; i++) {

                enemigos[i] = new Enemigo(naveEnemigaImg, lazerEnemigoImg, explosionSheet);

            }

            for (int i = 0; i < enemigosKamikaze.length; i++) {

                enemigosKamikaze[i] = new EnemigoKamikaze(enemigoKamikazeImg);

            }

        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        naveJugadorImg.dispose();
        lazerJugadorImg.dispose();
        naveEnemigaImg.dispose();
        enemigoKamikazeImg.dispose();
        fondoImg.dispose();
    }

}
