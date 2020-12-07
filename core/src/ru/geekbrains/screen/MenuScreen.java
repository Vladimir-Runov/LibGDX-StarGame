package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    Texture img;
    Texture imgbkg;
    Vector2 v1,v,p,p1,a;

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        super.show();

        img = new Texture("fball-4242.png");
        imgbkg = new Texture("backgroung1.png");
        v1 = new Vector2(0,0);
        p = new Vector2(0,0);
        p1 = new Vector2(0,0);
      //  a = new Vector2(0.1f,0.1f);
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
        v1.x = p1.x-p.x;
        v1.y = p1.y-p.y;
        float dx = p1.x-p.x;
        float dy = p1.y-p.y;
        if (dx != 0 || dy != 0) {
            v1.nor();
            p.add(v1);
      //      if (dx > 0) p.x++; else p.x--;
        }
        if (dy != 0) {
      //      if (dy > 0) p.y++; else p.y--;
        }

        batch.begin();
        batch.draw(imgbkg, 0, 0, 1000,1000);
        //batch.setColor(0.200f,0.200f,0.200f);
        batch.draw(img, p.x, p.y, 38,38);
        batch.end();
        //     p.add(v);
        //   v.add(a);

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        img.dispose();
        imgbkg.dispose();
        super.dispose();

    }

    /**
     * Called when a key was pressed
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.UP: p1.y+=10; break;
            case Input.Keys.DOWN: p1.y-=10; break;
            case Input.Keys.LEFT: p1.x-=10; break;
            case Input.Keys.RIGHT: p1.x+=10; break;

        }
        return false;
    }

    /**
     * Called when the screen was touched or a mouse button was pressed. The button parameter will be {@link Buttons#LEFT} on iOS.
     *
     * @param screenX The x coordinate, origin is in the upper left corner
     * @param screenY The y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event.
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        p1.set(screenX-img.getWidth()/2, Gdx.graphics.getHeight()-screenY-img.getHeight()/2);
        return false;
    }
}
