package com.iesfa.flappy.actors;

import static com.iesfa.flappy.extra.Utils.USER_PIPE_DOWN;
import static com.iesfa.flappy.extra.Utils.USER_PIPE_TOP;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Pipes extends Actor {


    private static final float PIPE_WIDTH = 0.85f;
    private static final float PIPE_HEIGHT = 4f;

    //Todo 1. Creamos textura,cuerpo y fixture para la tubería de arriba
    private TextureRegion pipeDownTR;
    private TextureRegion pipeTopTR;

    private Body bodyDown;
    private Body bodyTop;

    private Fixture fixtureDown;
    private Fixture fixtureTop;

    private World world;

    //Todo 2. Añadimos la textura para la tubería de arriba al constructor
    public Pipes(World world, TextureRegion trpDown, TextureRegion trpTop, Vector2 position) {
        this.world = world;
        this.pipeDownTR = trpDown;
        this.pipeTopTR = trpTop;
        createBodyPipeDown(position);
        createBodyPipeTop(); //No se le pasa la posición porque irá en función de la posición de la tubería de abajo
        createFixture();
    }




    private void createBodyPipeDown(Vector2 position) {
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.KinematicBody;
        bodyDown = world.createBody(def);
        bodyDown.setUserData(USER_PIPE_DOWN);

    }

    //Todo 3. Creamos el método para crear el cuerpo de la tubería de arriba
    private void createBodyPipeTop() {
        BodyDef def = new BodyDef();
        def.position.x = bodyDown.getPosition().x;  //
        def.position.y = bodyDown.getPosition().y + PIPE_HEIGHT;  //
        def.type = BodyDef.BodyType.KinematicBody;
        bodyTop = world.createBody(def);
        bodyTop.setUserData(USER_PIPE_TOP);
    }


    private void createFixture() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PIPE_WIDTH /2, PIPE_HEIGHT /2 );

        this.fixtureDown = bodyDown.createFixture(shape,8);
        //Todo 4.Como es la misma figura podemos aprovechar el método y la forma ya creada
        // para añadirla a la tubería de arriba
        this.fixtureTop = bodyTop.createFixture(shape, 8);
        shape.dispose();
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(bodyDown.getPosition().x, bodyDown.getPosition().y);
        batch.draw(this.pipeDownTR, bodyDown.getPosition().x - PIPE_WIDTH/2,bodyDown.getPosition().y - PIPE_HEIGHT/2,
                PIPE_WIDTH,PIPE_HEIGHT);

        //Todo 5. Dibujamos la imagen de la tubería superior similar al resto de elementos
        batch.draw(this.pipeTopTR, bodyTop.getPosition().x - PIPE_WIDTH/2,bodyTop.getPosition().y - PIPE_HEIGHT/2,
                PIPE_WIDTH,PIPE_HEIGHT);
    }


    public void detach(){
        bodyDown.destroyFixture(fixtureDown);
        world.destroyBody(bodyDown);

        //Todo 6. Destruimos los recursos
        bodyTop.destroyFixture(fixtureTop);
        world.destroyBody(bodyTop);
    }
}
