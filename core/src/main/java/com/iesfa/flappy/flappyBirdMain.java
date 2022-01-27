package com.iesfa.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class flappyBirdMain extends ApplicationAdapter {

	//Objeto encargado de 'comunicarse' con la tarjeta gráfica
	private SpriteBatch batch;

	//Objeto encargado de cargar la imagen de nuestros archvios a la memoria de la tarjeta gráfica.
	private Texture image;

	@Override
	public void create() {
		batch = new SpriteBatch();
		image = new Texture("libgdx.png");
	}

	@Override
	public void render() {

		//Estas lineas son necesarias para limpiar la pantalla y lo contenido en el buffer gráfico
		// de la iteración anterior
		Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		///Cuando vayamos a decirle a la gráfica lo que queramos dibujar debemos hacerlo por lotes.
		batch.begin();
		batch.draw(image, 140, 210);
		batch.end();
	}

	@Override
	public void dispose() {

		//Recordamos que tanto la imagen como el batch están cargados en la gráfica. Por lo que
		// cuando salgamos de la pantalla debemos liberar los recursos
		batch.dispose();
		image.dispose();
	}
}