package ru.geekbrains;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.screen.MenuScreen;

public class StarGame extends Game {
					//extends ApplicationAdapter {
	/**
	 * Called when the {@link Application} is first created.
	 */
	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}


//	@Override
//	public void create () {
//	}
//
//	@Override
//	public void render () {
//	}
//
//	@Override
//	public void dispose () {
//	}
}
