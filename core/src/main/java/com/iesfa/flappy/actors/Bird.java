package com.iesfa.flappy.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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



    private static final int STATE_NORMAL = 0;
    private static final int STATE_DEAD = 1;
    private static final float JUMP_SPEED = 5f;


    private int state;


    private Animation<TextureRegion> birdAnimation;
    private Vector2 position;


    private World world;

    private float stateTime;


    private Body body;

    private Fixture fixture;


    public Bird(World world,Animation<TextureRegion> animation, Vector2 position) {
        this.birdAnimation = animation;
        this.position      = position;
        this.world         = world;
        stateTime = 0f;
        state = STATE_NORMAL;
        createBody();
        createFixture();

    }


    public void createBody(){
        //Creamos BodyDef
        BodyDef bodyDef = new BodyDef();
        //Position
        bodyDef.position.set(position);

        //tipo
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        //createBody de mundo
        this.body = this.world.createBody(bodyDef);
        //setUserData  --> Utils -> identificadores de cuerpos
        this.body.setUserData(Utils.USER_BIRD);
    }


    public void createFixture(){
        //Shape
        CircleShape circle = new CircleShape();
        //radio
        circle.setRadius(0.25f);

        //createFixture
        this.fixture = this.body.createFixture(circle,3);

        //dispose
        circle.dispose();
    }



    @Override
    public void act(float delta) {


        boolean jump = Gdx.input.justTouched();

        if(jump && this.state == STATE_NORMAL){
            this.body.setLinearVelocity(0, JUMP_SPEED);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(body.getPosition().x-0.3f, body.getPosition().y-0.25f);
        batch.draw(this.birdAnimation.getKeyFrame(stateTime,true),getX(),getY(), 0.6f,0.5f);

        stateTime += Gdx.graphics.getDeltaTime();


    }


    public void detach(){

        //(body) destroyFixture
        this.body.destroyFixture(this.fixture);
        //(world) destroyBody
        this.world.destroyBody(this.body);

    }
}
