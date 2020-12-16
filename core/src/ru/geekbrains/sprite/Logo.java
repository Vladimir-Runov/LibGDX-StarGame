package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Logo extends Sprite {
    private Vector2 touch,tmp,v;
    private static final float V_LEN = 0.1f;

    public Logo(Texture texture)
    {
        super(new TextureRegion(texture));
        v = new Vector2();
        tmp = new Vector2();
        touch = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.pos.set(worldBounds.pos);  //super.resize(worldBounds);
        setHeightProportion(0.3f);
    }

    @Override
    public void update(float delta) {
        tmp.set(touch);
        if (tmp.sub(pos).len() < V_LEN)
            pos.set(touch);
        else
            pos.add(v);
        //super.update(delta);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        v.set(touch.cpy().sub(pos)).setLength(V_LEN);
        return false; //super.touchDown(touch, pointer, button);
    }
}
