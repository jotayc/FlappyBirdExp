package com.iesfa.flappy.screens;

import com.badlogic.gdx.Screen;
import com.iesfa.flappy.MainGame;

import sun.tools.jar.Main;


//Todo: Todas la pantallas deberán implementar la interfaz Screen ya que es la que controla el ciclo de vida
// de esta. ¿Habría una forma mejor de organizar el código?
public class GameScreen implements Screen {

    MainGame mainGame;
    //Todo : Toda pantalla debe tener una 'escena' que controle los elementos que aparecen en cada
    // pantalla (Podríamos decir que sería nuestro director de escena)

    public GameScreen(MainGame mainGame){
        this.mainGame = mainGame;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
