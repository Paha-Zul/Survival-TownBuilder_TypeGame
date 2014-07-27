package com.mygdx.game.interfaces;

import com.mygdx.game.component.buildings.Building;

public interface BuildingCriteria {
	public boolean withinCriteria(Building building);
}
