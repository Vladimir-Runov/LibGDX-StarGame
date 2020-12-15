package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;

public class MenuScreen extends BaseScreen {
    Texture imgbkg;
    private Background background;

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        super.show();

        imgbkg = new Texture("backgroung1.png");
        background = new Background(imgbkg);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0.2777f, 0.23f, 0.46f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch); //batch.draw(imgbkg, -1f,-1f,2.0f, 2.0f);
        //batch.setColor(0.200f,0.200f,0.200f);

        batch.end();
        //     p.add(v);
        //   v.add(a);

    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

        imgbkg.dispose();
        super.dispose();

    }

    /**
     * Called when a key was pressed
     */
    @Override
    public boolean keyDown(int keycode) {
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
        return background.touchDown(touch, pointer, button);
    }

}
