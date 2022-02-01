package com.iesfa.flappy.screens;

import static com.iesfa.flappy.extra.Utils.*;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.iesfa.flappy.MainGame;
import com.iesfa.flappy.actors.Bird;
import com.iesfa.flappy.extra.Utils;

import org.w3c.dom.Text;

import sun.tools.jar.Main;


public class GameScreen extends BaseScreen {


    //Toda pantalla debe tener una 'escena' que controle los elementos que aparecen en cada
    // pantalla (Podríamos decir que sería nuestro director de escena)
    private Stage stage;

    private Image background;

    private Bird bird;

    public GameScreen(MainGame mainGame){
        super(mainGame);

        //Inicializamos el stage creando previamente nuestro viewport
        FitViewport fitViewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGTH);
        this.stage = new Stage(fitViewport);


    }
    //Creamos un metodo que configure el fondos
    public void addBackground(){
        this.background = new Image(mainGame.assetManager.getBackground());
        this.background.setPosition(0,0);
        this.background.setSize(WORLD_WIDTH,WORLD_HEIGTH);
        this.stage.addActor(this.background);
    }

    @Override
    public void show() {

        //Añadimos el metodo que crea el fondo
        addBackground();
        Animation<TextureRegion> birdSprite = mainGame.assetManager.getBirdAnimation();
        this.bird = new Bird(birdSprite, new Vector2(1.35f ,4.75f ));
        this.stage.addActor(this.bird);


    }

    @Override
    public void render(float delta) {

        //Ordenamos al stage que dibuje.
        this.stage.draw();

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        //Nos acordamos de eliminar los recursos del stage
        this.stage.dispose();

    }
}
