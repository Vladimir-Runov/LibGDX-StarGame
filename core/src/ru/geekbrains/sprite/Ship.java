package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

/*
Реализовать спрайт корабля
Разрезать текстуру корабля на 2 части
*** Cделать управление кораблём с помощью тача и/или клавиатуры
 */
public class Ship extends Sprite {
    private Vector2 touch,tmp,v;
    private static final float V_LEN = 0.1f;
    Texture imgSh;

    public Ship()
    {
        super();
        imgSh  = new Texture("textures/mainAtlas.png");
        regions = new TextureRegion[2];
        regions[0] = new TextureRegion(imgSh, 911,86,198,243);
        regions[1] = new TextureRegion(imgSh, 1119,89,198,243);

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

  //  @Override
    public void dispose() {
        imgSh.dispose();
    //    super.dispose();
    }
}