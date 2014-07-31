package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.component.AIJobController;
import com.mygdx.game.component.EconomicInformation;
import com.mygdx.game.component.GridEntity;
import com.mygdx.game.component.TownController;
import com.mygdx.game.component.TownScala;
import com.mygdx.game.component.buildings.Building;
import com.mygdx.game.utility.Constants;

public class TownHall extends Entity{

	public TownHall(Vector2 position, float rotation, Texture graphic, int drawLevel) {
		super("TownHall", position, rotation, graphic, drawLevel);
		
		this.addComponent(new EconomicInformation(this, "EcoInfo", 0, true));
		this.addComponent(new TownController(this, "TownController", 0, true));
		//this.addComponent(new AIJobController(this, "TownJobController", 0, true));
		this.addComponent(new GridEntity(this, "GridEntity", 0, false, Constants.GRID_STATIC));
		this.addComponent(new TownScala(this, "Town"));
		this.addComponent(new Building(this, "TownHall", Constants.BUILDING_TOWNHALL, destroyed));
		
		this.entityType = Constants.ENTITY_BUILDING;
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}

}
