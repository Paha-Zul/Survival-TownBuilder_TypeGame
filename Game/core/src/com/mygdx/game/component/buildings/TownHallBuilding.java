package com.mygdx.game.component.buildings;

import com.mygdx.game.entity.Entity;

public class TownHallBuilding extends Building {

	public TownHallBuilding(Entity owner, String name, int type, boolean active) {
		super(owner, name, type, active);
		
		
	}

	public TownHallBuilding(Entity owner, int buildingType) {
		this(owner, "GenericTownHall", buildingType, false);
		
	}

}
