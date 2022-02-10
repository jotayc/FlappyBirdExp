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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.iesfa.flappy.MainGame;
import com.iesfa.flappy.actors.Bird;
import com.iesfa.flappy.actors.Pipes;
import com.iesfa.flappy.extra.Utils;

import org.w3c.dom.Text;

import sun.tools.jar.Main;


public class GameScreen extends BaseScreen {

    private Stage stage;

    private Image background;


    private Bird bird;
    private Pipes pipes;

    private World world;

    //Todo 8. Creamos objeto MusicGame para la musica de fondo


    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera ortCamera;

    public GameScreen(MainGame mainGame){
        super(mainGame);


        this.world = new World(new Vector2(0,-10),true);

        FitViewport fitViewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGTH);
        this.stage = new Stage(fitViewport);

        //Todo 9. Inicializamos el objeto desde la instancia desde assetMan

        this.ortCamera = (OrthographicCamera) this.stage.getCamera();
        this.debugRenderer = new Box2DDebugRenderer();


    }



    //Todo 12 **Mejora de código** Organizar código para bird y pipes
    @Override
    public void show() {
        addBackground();
        addFloor();
        addRoof();

        Animation<TextureRegion> birdSprite = mainGame.assetManager.getBirdAnimation();
        TextureRegion pipeDownTexture = mainGame.assetManager.getPipeDownTR();

        TextureRegion pipeTopTexture = mainGame.assetManager.getPipeUpTR();

        //Todo 7. Le pasamos al constructor el sonido
        this.bird = new Bird(this.world,birdSprite, new Vector2(1.35f ,4.75f ));

        //Como ambas tuberías están en la misma clase solo debemos instanciar un objeto
        this.pipes = new Pipes(this.world, pipeDownTexture, pipeTopTexture,new Vector2(3.75f,2f)); //Posición de la tubería inferior

        this.stage.addActor(this.bird);
        this.stage.addActor(this.pipes);

        //Todo 10. Reproducimos la música cuando aparezca la pantalla
           //play
           //loop
    }


    public void addRoof(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);

        EdgeShape edge = new EdgeShape();
        edge.set(0,WORLD_HEIGTH,WORLD_WIDTH,WORLD_HEIGTH);
        body.createFixture(edge, 1);
        edge.dispose();
    }

    private void addFloor() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(WORLD_WIDTH / 2f, 0.6f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);
        body.setUserData(USER_FLOOR);

        PolygonShape edge = new PolygonShape();
        edge.setAsBox(2.3f, 0.5f);
        body.createFixture(edge, 3);
        edge.dispose();
    }

    public void addBackground(){
        this.background = new Image(mainGame.assetManager.getBackground());
        this.background.setPosition(0,0);
        this.background.setSize(WORLD_WIDTH,WORLD_HEIGTH);
        this.stage.addActor(this.background);
    }

    @Override
    public void render(float delta) {

        //this.stage.getBatch().setProjectionMatrix(ortCamera.combined);

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
        //detach
        this.bird.detach();
        //remove
        this.bird.remove();


        this.pipes.detach();
        this.pipes.remove();


        //Todo 11.Paramos la música cuando se oculte la pantalla
    }

    @Override
    public void dispose() {

        this.stage.dispose();
        this.world.dispose();

    }
}
