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

    float stateTime;


    //5. Creamos el atributo para el cuerpo del pájaro.

    //6. Creamos el atributo para la forma del pájaro.


    //4. Modificamos el constructor para pasarle la instancia del mundo físico.
    public Bird(Animation<TextureRegion> animation, Vector2 position) {
        this.birdAnimation = animation;
        this.position      = position;

        stateTime = 0f;
    }

    //7. Creamos un método para crear el cuerpo
    public void createBody(){
        //Creamos BodyDef

        //Position


        //tipo


        //createBody de mundo

    }

    //8. Creamos un método para crear la forma
    public void createFixture(){
        //Shape

        //radio


        //createFixture

        //setUserData  --> Utils -> identificadores de cuerpos

        //dispose

    }



    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.birdAnimation.getKeyFrame(stateTime,true),position.x,position.y, 0.6f,0.5f);

        stateTime += Gdx.graphics.getDeltaTime();


    }

    //9.Nos creamos un metodo detach que nos ayudará a liberar los recursos de body y fixture
    public void detach(){

        //(body) destroyFixture

        //(world) destroyBody

    }
}
