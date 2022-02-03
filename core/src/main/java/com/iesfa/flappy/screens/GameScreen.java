package com.iesfa.flappy.screens;

import static com.iesfa.flappy.extra.Utils.*;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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


    //**12** DebugRenderer nos servirá para ver en la pantalla una representación gráfica del mundo físico.
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera ortCamera;

    public GameScreen(MainGame mainGame){
        super(mainGame);

        //2.Creamos el mundo recibiendo dos parametros
        this.world = new World(new Vector2(0,-10),true);

        FitViewport fitViewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGTH);
        this.stage = new Stage(fitViewport);

        //13. Cargamos la cámara
        this.ortCamera = (OrthographicCamera) this.stage.getCamera();
        this.debugRenderer = new Box2DDebugRenderer();



    }


    //Todo alumno: Crear un método que añada el 'cuerpo' y la 'forma' del suelo


    @Override
    public void show() {


        addBackground();
        Animation<TextureRegion> birdSprite = mainGame.assetManager.getBirdAnimation();


        //11.Hay que pasarle el mundo al constructor de Bird para que este configure su física
        this.bird = new Bird(this.world,birdSprite, new Vector2(1.35f ,4.75f ));
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

        this.stage.getBatch().setProjectionMatrix(ortCamera.combined);
        //10.Pedimos a draw que actualice la físca de los actores que tiene adscritos
        this.stage.act();
        this.world.step(delta,6,2); //Porqué 6 y 2? Por que así lo dice la documentación.
        this.stage.draw();

        //Actualizamos la cámara para que aplique cualquier cambio en las matrices internas.
        this.ortCamera.update();
        // Se le pasa el mundo físico y las matrices de la camara (combined)
        this.debugRenderer.render(this.world, this.ortCamera.combined);
    }

    @Override
    public void hide() {
        //12. Nos acordamos que cuando el usuario no esté jugando (no esté la pantalla activa)
        //quitamos los recursos de los actores de memoria

        //detach
        this.bird.detach();
        //remove
        this.bird.remove();
    }

    @Override
    public void dispose() {


        this.stage.dispose();

        //3.Nos acordamos de eliminar los recursos de que retiene world
        this.world.dispose();

    }
}
