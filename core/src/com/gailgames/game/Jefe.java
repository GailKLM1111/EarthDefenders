package com.gailgames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class Jefe {

    public Vector2 posicion;
    public Sprite jefe;
    public Boolean vivo = false;
    public int salud = 100;
    public LazerEnemigo[] lazerArray;
    long ultimoAtaque;
    long tiempoPrimeraAparicion;
    boolean primeraAparicion;
    public Jefe(Texture jefeImg, Texture lazerImg) {

        posicion = new Vector2(Gdx.graphics.getWidth()/2 - 1000/2, Gdx.graphics.getHeight() - 750);
        jefe = new Sprite(jefeImg);
        jefe.setScale(1);
        lazerArray = new LazerEnemigo[30];
        ultimoAtaque = TimeUtils.millis();
        primeraAparicion = true;

        Sprite lazer = new Sprite(lazerImg);

        for (int i = 0; i < lazerArray.length; i++) {

            lazerArray[i] = new LazerEnemigo(lazer);

        }

    }

    public void Atacar() {

        if (TimeUtils.timeSinceMillis(ultimoAtaque) > 2250 && TimeUtils.timeSinceMillis(tiempoPrimeraAparicion) > 2500) {

            ultimoAtaque = TimeUtils.millis();
            TipoAtaque();

            for (int i = 0; i < lazerArray.length; i++) {

                if (lazerArray[i].vivo) {

                    lazerArray[i].velocidad = 520;
                    lazerArray[i].posicion.x = 36 * i + lazerArray[i].lazerEnemigo.getWidth();
                    lazerArray[i].posicion.y = posicion.y;

                }

            }

        }

    }

    public void TipoAtaque() {

        int probabilidad = MathUtils.random(1, 5);

        if (probabilidad == 1 || probabilidad == 4) {

            for (int i = 0; i < lazerArray.length; i++) {

                if (i >= 2 && i <= 7) {

                    lazerArray[i].vivo = false;

                } else {

                    lazerArray[i].vivo = true;

                }

            }

        }

        if (probabilidad == 2) {

            for (int i = 0; i < lazerArray.length; i++) {

                if (i >= 12 && i <= 17) {

                    lazerArray[i].vivo = false;

                } else {

                    lazerArray[i].vivo = true;

                }

            }

        }

        if (probabilidad == 3 || probabilidad == 5) {

            for (int i = 0; i < lazerArray.length; i++) {

                if (i >= 22 && i <= 27) {

                    lazerArray[i].vivo = false;

                } else {

                    lazerArray[i].vivo = true;

                }

            }

        }

    }

    public void Dañar() {

        salud -= 1;

        if (salud <= 0) {

            Desaparecer();

        }

    }

    public void Desaparecer() {

        posicion.y = 1000000;
        vivo = false;

    }

    public void Pintar(SpriteBatch batch, long tiempo) {

        if (primeraAparicion) {

            tiempoPrimeraAparicion = tiempo;
            primeraAparicion = false;

        }

        for (int i = 0; i < lazerArray.length; i++) {

            lazerArray[i].Pintar(batch);

        }

        Atacar();
        jefe.setPosition(posicion.x, posicion.y);
        jefe.draw(batch);

    }


}
