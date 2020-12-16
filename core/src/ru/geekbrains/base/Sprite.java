package ru.geekbrains.base;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.Rect;
//import ru.geekbrains.utils.Regions;

public class Sprite extends Rect {
  //  private static final float LEN_V = 0.001f;
    private float angle;
    private float scale = 1;
    protected TextureRegion[] regions;
    private int frame;
    private boolean destroyed;

//    Vector2 v,p,touch,tmp;

    public Sprite() {
    }

    public Sprite(TextureRegion region) {

    //    v = new Vector2(0.0001f,0.0002f);
     //   p = new Vector2(0,0);
     //   tmp = new Vector2(0,0);
     //   touch = new Vector2(0,0);

        regions = new TextureRegion[1];
        regions[0] = region;
    }

  //  public Sprite(TextureRegion region, int rows, int cols, int frames) {
  //      regions = Regions.split(region, rows, cols, frames);
  //  }

    public void draw(SpriteBatch batch) {
        batch.draw(regions[frame], getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(), scale, scale, angle );
 //       tmp.set(touch);
 //       if (tmp.sub(p).len() > LEN_V)   p.add(v);
 //       else                            p.set(touch);
        //
        //       batch.draw(img, p.x, p.y, 0.065f,0.065f);
    }

    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);
    }

    public void update(float delta) {
    }

    public void resize(Rect worldBounds) {

    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
 //       this.touch.set(touch);
 //       this.v.set(touch.cpy().sub(p).sub(0f,0f)).setLength(LEN_V);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
 //       this.touch.set(touch);
 //       this.v.set(touch.cpy().sub(p)).setLength(LEN_V);
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer, int button) {
        return false;
    }


    public boolean keyDown(int keycode) {
  //      float dP = 0.2f;
  //      switch (keycode){
  //          case Input.Keys.UP:    touch.y += dP; touch.setLength(LEN_V); break;
  //          case Input.Keys.DOWN:  touch.y -= dP; touch.setLength(LEN_V); break;
  //          case Input.Keys.LEFT:  touch.x -= dP; touch.setLength(LEN_V); break;
  //          case Input.Keys.RIGHT: touch.x += dP; touch.setLength(LEN_V); break;
  //      }
        return false;
    }


    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void destroy() {
   //     img.dispose();
        destroyed = true;
    }

    public void flushDestroy() {
        destroyed = false;
    }
}
