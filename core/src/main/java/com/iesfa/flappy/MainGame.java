package com.iesfa.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.iesfa.flappy.screens.GameOverScreen;
import com.iesfa.flappy.screens.GameScreen;
import com.iesfa.flappy.screens.GetReadyScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {

	//Instancia de la pantalla durante el juego
	public GameScreen gameScreen;

	//Todo alumno: Crear instancia de la pantalla de GetReady
	public GetReadyScreen getReadyScreen;

	//Todo alumno: Crear instancia de la pantalla de GameOver
	public GameOverScreen gameOverScreen;

	@Override
	public void create() {

		this.gameScreen = new GameScreen(this);

		//Scene2d nos ayuda a manejar las diferentes instancias de las diferentes pantallas que
		//compondrá nuestro juego.
		setScreen(this.gameScreen);
	}

}