package com.iesfa.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.iesfa.flappy.extra.AssetMan;
import com.iesfa.flappy.screens.GameOverScreen;
import com.iesfa.flappy.screens.GameScreen;
import com.iesfa.flappy.screens.GetReadyScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {


	public GameScreen gameScreen;


	public GetReadyScreen getReadyScreen;


	public GameOverScreen gameOverScreen;

	public AssetMan assetManager;

	@Override
	public void create() {
		this.assetManager = new AssetMan();

		this.gameScreen = new GameScreen(this);
		this.gameOverScreen = new GameOverScreen(this);
		this.getReadyScreen = new GetReadyScreen(this);


		setScreen(this.gameScreen);


	}

}