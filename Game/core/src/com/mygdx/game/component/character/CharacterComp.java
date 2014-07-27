package com.mygdx.game.component.character;

import com.mygdx.game.component.Component;
import com.mygdx.game.component.buildings.ResidenceScala;
import com.mygdx.game.entity.Entity;

public class CharacterComp extends Component {
	ResidenceScala home;

	public CharacterComp(Entity owner, String name, int type, boolean active) {
		super(owner, name, type, active);
		
	}
	
	@Override
	public void update(float delta){
		
	}
	
	public void setHome(ResidenceScala home){
		this.home = home;
	}

}
