package com.iesfa.flappy.extra;


//Esta clase centraliza la gestión de los recursos gráficos como texturas, regiones, o animaciones.


import static com.iesfa.flappy.extra.Utils.ATLAS_MAP;
import static com.iesfa.flappy.extra.Utils.BACKGROUND_IMAGE;
import static com.iesfa.flappy.extra.Utils.MUSIC_BG;
import static com.iesfa.flappy.extra.Utils.PIPE_DAWN;
import static com.iesfa.flappy.extra.Utils.PIPE_UP;
import static com.iesfa.flappy.extra.Utils.SOUND_JUMP;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetMan {

    private AssetManager assetManager;
    private TextureAtlas textureAtlas;



    public AssetMan() {
        this.assetManager = new AssetManager();

        assetManager.load(ATLAS_MAP, TextureAtlas.class);

          //2.1 Sound
        assetManager.load(SOUND_JUMP, Sound.class);
          //2.2 Music
        assetManager.load(MUSIC_BG, Music.class);

        assetManager.finishLoading();

        textureAtlas = assetManager.get(ATLAS_MAP);
    }

    //Creamos un metodo que devuelva la parte de la imagen que corresponde al fondo.
    public TextureRegion getBackground() {
        return this.textureAtlas.findRegion(BACKGROUND_IMAGE);
    }

    public Animation<TextureRegion> getBirdAnimation(){
        return new Animation<TextureRegion>(0.33f,
                textureAtlas.findRegion("bird1"),
                textureAtlas.findRegion("bird2"),
                textureAtlas.findRegion("bird3"));
    }

    public TextureRegion getPipeDownTR() {
        return  this.textureAtlas.findRegion(PIPE_DAWN);
    }

    public TextureRegion getPipeUpTR() {
        return  this.textureAtlas.findRegion(PIPE_UP);
    }

    public Sound getJumpSound(){
        return this.assetManager.get(SOUND_JUMP);
    }

    public Music getMusicBG(){
        return this.assetManager.get(MUSIC_BG);
    }
}
