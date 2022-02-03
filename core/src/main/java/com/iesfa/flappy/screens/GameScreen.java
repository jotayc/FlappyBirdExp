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



    private Stage stage;

    private Image background;

    private Bird bird;

    //1.World se encarga de gestionar el mundo físico dentro de nuestro juego
    private World world;


    public GameScreen(MainGame mainGame){
        super(mainGame);

        //2.Creamos el mundo recibiendo dos parametros


        FitViewport fitViewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGTH);
        this.stage = new Stage(fitViewport);



    }


    //Todo alumno: Crear un método que añada el 'cuerpo' y la 'forma' del suelo


    @Override
    public void show() {


        addBackground();
        Animation<TextureRegion> birdSprite = mainGame.assetManager.getBirdAnimation();


        //11.Hay que pasarle el mundo al constructor de Bird para que este configure su física
        this.bird = new Bird(birdSprite, new Vector2(1.35f ,4.75f ));
        this.stage.addActor(this.bird);


    }

    public void addBackground(){
        this.background = new Image(mainGame.assetManager.getBackground());
        this.background.setPosition(0,0);
        this.background.setSize(WORLD_WIDTH,WORLD_HEIGTH);
        this.stage.addActor(this.background);
    }

    @Override
    public void render(float delta) {

        //10.Pedimos a draw que actualice la físca de los actores que tiene adscritos

        //this.world.step(delta,6,2); //Porqué 6 y 2? Por que así lo dice la documentación.
        this.stage.draw();

    }

    @Override
    public void hide() {
        //12. Nos acordamos que cuando el usuario no esté jugando (no esté la pantalla activa)
        //quitamos los recursos de los actores de memoria

        //detach

        //remove
    }

    @Override
    public void dispose() {


        this.stage.dispose();

        //3.Nos acordamos de eliminar los recursos de que retiene world

    }
}
