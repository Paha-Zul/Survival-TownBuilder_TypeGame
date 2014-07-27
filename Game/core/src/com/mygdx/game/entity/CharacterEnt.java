package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utility.Constants;

public class CharacterEnt extends Entity{

	public CharacterEnt(String name, Vector2 position, float rotation, Texture graphic, int drawLevel) {
		super(name, position, rotation, graphic, drawLevel);
		// TODO Auto-generated constructor stub
		
		this.entityType = Constants.ENTITY_CHARACTER;
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
