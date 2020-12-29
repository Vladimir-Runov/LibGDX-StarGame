package ru.geekbrains.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.ExplosionPool;

/*
Реализовать спрайт корабля
Разрезать текстуру корабля на 2 части
*** Cделать управление кораблём с помощью тача и/или клавиатуры
-------
Урок 5 -  Сдайте задание до: 21 дек., 19:00 +03:00
Разобраться с классами
  Sound(http://www.libgdx.ru/2013/10/sound-effects.html)
и Music(http://www.libgdx.ru/2013/10/streaming-music.html)
(можно мне вопросы задавать) и реализовать фоновую музыку и звуки выстрелов
Реализовать автоматическую стрельбу корабля
 */
public class Ship extends Sprite {
    protected static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;

    protected TextureRegion bulletRegion;
    protected Sound bulletSound;
    protected Vector2 bulletV;
    protected Vector2 bulletPos;

    protected float bulletHeight;
    protected int damage;
    protected int hp;

    protected Vector2 v;
    protected Vector2 v0;

    protected Rect worldBounds;

    protected float reloadInterval;
    protected float reloadTimer;
    protected float damageAnimateTimer;

    private final BulletPool bulletPool;
    private final ExplosionPool explosionPool;

    public Ship(BulletPool bulletPool, ExplosionPool explosionPool) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;
        v = new Vector2(0,0);
    }

    public Ship(TextureRegion region, int rows, int cols, int frames, BulletPool bulletPool, ExplosionPool explosionPool) {
        super(region, rows, cols, frames);
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL) {
            frame = 0;
        }
    }


    public void damage(int damage) {
        this.hp -= damage;
        if (hp <= 0) {
            hp = 0;
            destroy();
        }
        frame = 1;
        damageAnimateTimer = 0f;
    }


    public int getDamage() {
        return damage;
    }

    private void shoot() {
        bulletSound.play(0.5f);
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, bulletHeight, worldBounds, damage);
    }

    private void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(this.pos, getHeight());
    }

    @Override
    public void resize(Rect worldBounds) {
        this.pos.set(worldBounds.pos);  //super.resize(worldBounds);
        setHeightProportion(0.3f);
    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
 //       this.touch.set(touch);
  //      v.set(touch.cpy().sub(pos)).setLength(V_LEN);
        return false; //super.touchDown(touch, pointer, button);
    }

  //  @Override
    public void dispose() {
//        imgSh.dispose();
        super.destroy();
        boom();
        //    super.dispose();
    }
    @Override
    public void destroy() {
        super.destroy();
        boom();
    }
}