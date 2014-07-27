package com.mygdx.game.component;

import com.mygdx.game.entity.Entity;

public class CharacterNeeds extends Component {
	public int maxHunger, currHunger;
	public int maxFatigue, currFatigue;
	public int maxThirst, currThirst;

	public CharacterNeeds(Entity owner, String name, int type, boolean active) {
		super(owner, name, type, active);
		
		this.maxHunger = 100;
		this.maxFatigue = 100;
		this.maxThirst = 100;
	}

}
