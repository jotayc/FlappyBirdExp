package com.iesfa.flappy.screens;

import com.badlogic.gdx.Screen;
import com.iesfa.flappy.MainGame;

import sun.tools.jar.Main;


//Todo: Todas la pantallas deberán implementar la interfaz Screen ya que es la que controla el ciclo de vida
// de esta. ¿Habría una forma mejor de organizar el código?
public class GameScreen extends BaseScreen {


    //Todo : Toda pantalla debe tener una 'escena' que controle los elementos que aparecen en cada
    // pantalla (Podríamos decir que sería nuestro director de escena)

    public GameScreen(MainGame mainGame){
        super(mainGame);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
