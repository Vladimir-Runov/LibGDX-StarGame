package ru.geekbrains.screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.ButtonExit;
import ru.geekbrains.sprite.ButtonPlay;
import ru.geekbrains.sprite.Logo;
import ru.geekbrains.sprite.Star;
import ru.geekbrains.sprite.Star;

public class MenuScreen extends BaseScreen {
    private static final int STAR_COUNT = 256;

    Texture imgbkg;
 //   Texture img;
    private TextureAtlas atlas;
    private Background background;
    //private Logo logo;

    private ButtonExit buttonExit;
    private ButtonPlay buttonPlay;
    private Star[] stars;
    private final Game game;

    public MenuScreen(Game game) {
        this.game = game;
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        super.show();
    //    img = new Texture("textures/fball-4242.png");
       // logo = new Logo(img);
        imgbkg = new Texture("textures/backgroung1.png");
        background = new Background(imgbkg);

        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }
        buttonExit = new ButtonExit(atlas);
        buttonPlay = new ButtonPlay(atlas, game);
    }

    /**
     * Called when the screen should render itself.
     */
    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
     //   logo.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }

        buttonExit.resize(worldBounds);
        buttonPlay.resize(worldBounds);
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        imgbkg.dispose();
 //       img.dispose();

        atlas.dispose();
        super.dispose();
    }

    /**
     * Called when a key was pressed
     */
    @Override
    public boolean keyDown(int keycode)
    {
        return background.keyDown(keycode);
    }

    /**
     * Called when the screen was touched or a mouse button was pressed. The button parameter will be {@link Buttons#LEFT} on iOS.
     */
//    @Override
 //  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    //        p1.set(screenX-img.getWidth()/2, Gdx.graphics.getHeight()-screenY-img.getHeight()/2);
    //        return false;
 //  }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
    //    logo.touchDown(touch,pointer, button);
        buttonExit.touchDown(touch, pointer, button);
        buttonPlay.touchDown(touch, pointer, button);
        return super.touchDown(touch, pointer, button);
    }
    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        buttonExit.touchUp(touch, pointer, button);
        buttonPlay.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        //      logo.update(delta);
        for (Star star : stars) {
            star.update(delta);
        }
    }



    private void draw() {
        Gdx.gl.glClearColor(0.2777f, 0.23f, 0.46f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
                background.draw(batch); //batch.draw(imgbkg, -1f,-1f,2.0f, 2.0f);
        //      batch.setColor(0.200f,0.200f,0.200f);
      //        logo.draw(batch);
                for (Star star : stars) {
                    star.draw(batch);
                }
                buttonExit.draw(batch);
                buttonPlay.draw(batch);
      batch.end();
    }
}
