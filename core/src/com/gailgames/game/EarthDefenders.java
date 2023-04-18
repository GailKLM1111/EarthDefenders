package com.gailgames.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

//import java.awt.*;
import java.util.Iterator;

public class EarthDefenders extends ApplicationAdapter {
	SpriteBatch batch;

	SpriteBatch batch2;
	Texture img;

	Texture naveEnemiga;

	Texture naveJugador;

	Texture lazerJugador;
	Sound gotitasSound;

	Music gotitasMusic;

	OrthographicCamera camera;

	Rectangle naveJugadorHitBox;

	Rectangle lazerJugadorHitBox;

	Array<Rectangle> gotitasArray;

	Array<Rectangle> lazerJugadorArray;

	long tiempoUltimaGotita;

	long tiempoUltimoLazer;

	boolean lazerVivo = false;
	
	@Override
	public void create () {

		naveEnemiga = new Texture(Gdx.files.internal("naveEnemiga.png"));
		naveJugador = new Texture(Gdx.files.internal("naveJugador.png"));

		lazerJugador = new Texture(Gdx.files.internal("lazerJugador.png"));

		gotitasSound = Gdx.audio.newSound(Gdx.files.internal("gotitas.mp3"));
		gotitasMusic = Gdx.audio.newMusic(Gdx.files.internal("AllemIversomTheRidge.wav"));

		gotitasMusic.setLooping(true);
		gotitasMusic.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1080, 1920);
		batch = new SpriteBatch();

		naveJugadorHitBox = new Rectangle();
		naveJugadorHitBox.x = 1080/2 - 160/2;
		naveJugadorHitBox.y = 30;
		naveJugadorHitBox.width = 160;
		naveJugadorHitBox.height = 90;

		lazerJugadorHitBox = new Rectangle();
		lazerJugadorHitBox.x = -100;
		lazerJugadorHitBox.y = -100;
		lazerJugadorHitBox.width = 10;
		lazerJugadorHitBox.height = 50;

		gotitasArray = new Array<Rectangle>();
		generarGotitas();

//		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(10, 0, 0.2f, 1);

		camera.update();

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(naveJugador, naveJugadorHitBox.x, naveJugadorHitBox.y);
		batch.draw(lazerJugador, lazerJugadorHitBox.x , lazerJugadorHitBox.y);
		for (Rectangle gotita : gotitasArray) {
			batch.draw(naveEnemiga, gotita.x, gotita.y);

		}
		batch.end();

		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			naveJugadorHitBox.x = (int) (touchPos.x - 64 / 2);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) naveJugadorHitBox.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) naveJugadorHitBox.x += 200 * Gdx.graphics.getDeltaTime();

		if (lazerVivo == false) {
			if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

				lazerJugadorHitBox.x = naveJugadorHitBox.x + 75;

				lazerJugadorHitBox.y += naveJugadorHitBox.y + 200 * Gdx.graphics.getDeltaTime();

				if (lazerJugadorHitBox.y > 1920) lazerJugadorHitBox.y = naveJugadorHitBox.y + 90;


			}
		}

		if (naveJugadorHitBox.x < 0) naveJugadorHitBox.x = 0;
		if (naveJugadorHitBox.x > 1080 - 160) naveJugadorHitBox.x = 1080 - 160;

		if (TimeUtils.nanoTime() - tiempoUltimaGotita > 1000000000) generarGotitas();

		for (Iterator<Rectangle> iter = gotitasArray.iterator(); iter.hasNext(); ) {
			Rectangle gotita = iter.next();
			gotita.y -= 200 * Gdx.graphics.getDeltaTime();
			if (gotita.y + 64 < 0) iter.remove();

			if (gotita.overlaps(naveJugadorHitBox)) {
				gotitasSound.play();
				iter.remove();
			}

		}

	}

	private void generarGotitas() {
		Rectangle raindrop = new Rectangle();

		raindrop.x = MathUtils.random(0, 1080-160);

//		raindrop.x = MathUtils.random(0, 200-64);
//		raindrop.x = MathUtils.random(0, 480-64);
		raindrop.y = 800;
		raindrop.width = 160;
		raindrop.height = 90;
		gotitasArray.add(raindrop);
		tiempoUltimaGotita = TimeUtils.nanoTime();
	}

	private void generarLazer(SpriteBatch batch) {

		Rectangle lazerJuagadorHitBox = new Rectangle();

		lazerJuagadorHitBox.x = naveJugadorHitBox.x + 75;

		lazerJuagadorHitBox.y = naveJugadorHitBox.y + 90;

		lazerJuagadorHitBox.width = 10;

		lazerJuagadorHitBox.height = 50;



		tiempoUltimoLazer = TimeUtils.nanoTime();

		if (lazerVivo == false) {

			lazerVivo = true;

			while (lazerJuagadorHitBox.y < 1920) {
//				batch.draw(lazerJugador, lazerJuagadorHitBox.x, lazerJuagadorHitBox.y);
				lazerJuagadorHitBox.y += 10000 * Gdx.graphics.getDeltaTime();
			}

//			lazerVivo = false;

		}

//		batch2.draw(lazerJugador, lazerJuagadorHitBox.x, lazerJuagadorHitBox.y);

		lazerJuagadorHitBox.y += 200 * Gdx.graphics.getDeltaTime();

//				tiempoUltimoLazer = TimeUtils.nanoTime();





	}


	@Override
	public void dispose () {
		naveEnemiga.dispose();
		naveJugador.dispose();
		gotitasSound.dispose();
		gotitasMusic.dispose();
		batch.dispose();
	}
}
