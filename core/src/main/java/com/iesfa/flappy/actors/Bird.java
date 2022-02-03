package com.iesfa.flappy.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.iesfa.flappy.extra.Utils;

public class Bird extends Actor {


    private Animation<TextureRegion> birdAnimation;
    private Vector2 position;


    private World world;

    private float stateTime;


    //5. Creamos el atributo para el cuerpo del pájaro.
    private Body body;
    //6. Creamos el atributo para la forma del pájaro.
    private Fixture fixture;

    //4. Modificamos el constructor para pasarle la instancia del mundo físico.
    public Bird(World world,Animation<TextureRegion> animation, Vector2 position) {
        this.birdAnimation = animation;
        this.position      = position;
        this.world         = world;
        stateTime = 0f;
        createBody();
        createFixture();

    }

    //7. Creamos un método para crear el cuerpo
    public void createBody(){
        //Creamos BodyDef
        BodyDef bodyDef = new BodyDef();
        //Position
        bodyDef.position.set(position);

        //tipo
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        //createBody de mundo
        this.body = this.world.createBody(bodyDef);
    }

    //8. Creamos un método para crear la forma
    public void createFixture(){
        //Shape
        CircleShape circle = new CircleShape();
        //radio
        circle.setRadius(0.3f);

        //createFixture
        this.fixture = this.body.createFixture(circle,8);
        //setUserData  --> Utils -> identificadores de cuerpos
        this.fixture.setUserData(Utils.USER_BIRD);
        //dispose
        circle.dispose();
    }



    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(body.getPosition().x-0.3f, body.getPosition().y-0.25f);
        batch.draw(this.birdAnimation.getKeyFrame(stateTime,true),getX(),getY(), 0.6f,0.5f);

        stateTime += Gdx.graphics.getDeltaTime();


    }

    //9.Nos creamos un metodo detach que nos ayudará a liberar los recursos de body y fixture
    public void detach(){

        //(body) destroyFixture
        this.body.destroyFixture(this.fixture);
        //(world) destroyBody
        this.world.destroyBody(this.body);

    }
}
