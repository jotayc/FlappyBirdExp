package com.iesfa.flappy.extra;


//Esta clase centraliza la gestión de los recursos gráficos como texturas, regiones, o animaciones.


import static com.iesfa.flappy.extra.Utils.ATLAS_MAP;
import static com.iesfa.flappy.extra.Utils.BACKGROUND_IMAGE;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetMan {

    private AssetManager assetManager;
    private TextureAtlas textureAtlas;



    public AssetMan() {
        this.assetManager = new AssetManager();

        assetManager.load(ATLAS_MAP, TextureAtlas.class);
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
}
