package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.utility.ItemBank;
import com.mygdx.game.utility.ItemImporter;

public class SpaceGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	
	OrthographicCamera camera;
	AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		font = new BitmapFont();
		
		new ItemBank();
		ItemImporter.importItems();
		//ItemImporterScala.importItems();
		ItemBank.calculateItemsList();
		
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
