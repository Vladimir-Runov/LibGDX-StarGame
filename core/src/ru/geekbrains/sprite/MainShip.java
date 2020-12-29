package ru.geekbrains.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.ExplosionPool;

public class MainShip extends Ship {

    private static final float HEIGHT = 0.15f;
    private static final float BOTTOM_MARGIN = 0.05f;

    private static final int INVALID_POINTER = -1;

//    private final BulletPool bulletPool;
//    private TextureRegion bulletRegion;
//    private Vector2 bulletV;

//    private final Vector2 v;
//    private final Vector2 v0;

 //   private Rect worldBounds;

    private boolean pressedLeft;
    private boolean pressedRight;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;
    private Sound soundShoot;
    private boolean shootAuto;
    private  int shootdelay = 100;

    private static final int HP = 1;
    private static final float RELOAD_INTERVAL = 0.2f;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool) {
        //super(atlas.findRegion("main_ship"), 1, 2, 2);
        super(atlas.findRegion("main_ship"), 1, 2, 2, bulletPool, explosionPool);

        bulletRegion = atlas.findRegion("bulletMainShip");
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletV = new Vector2(0, 0.5f);
        bulletPos = new Vector2();
        bulletHeight = 0.01f;

        v = new Vector2();
        v0 = new Vector2(0.5f, 0);
        reloadInterval = RELOAD_INTERVAL;
        hp = HP;

////        this.bulletPool = bulletPool;
//       soundShoot  = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
//        shootAuto = false;
//        shootdelay = 10;
        damage = 1;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        bulletPos.set(pos.x, pos.y + getHalfHeight());
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                pressedLeft = true;
                moveLeft();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    public void dispose() {
        bulletSound.dispose();
    }

    public boolean isBulletCollision(Bullet bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > pos.y
                || bullet.getTop() < getBottom());
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void moveRight() {
        v.set(v0);
    }

    private void stop() {
        v.setZero();
    }
    public void startNewGame() {
        stop();

        hp = HP;
        flushDestroy();

        pressedLeft =false;
        pressedRight=false;
        leftPointer=INVALID_POINTER;
        rightPointer=INVALID_POINTER;

        this.pos.x = worldBounds.pos.x;
        frame = 0;
        damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;
    }
}
