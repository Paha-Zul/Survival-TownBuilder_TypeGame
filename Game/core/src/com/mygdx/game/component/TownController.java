package com.mygdx.game.component;

import com.mygdx.game.entity.Entity;
import com.mygdx.game.utility.Timer;

public class TownController extends Component{
	Timer timer;
	
	int population=0;

	public TownController(Entity owner, String name, int type, boolean active) {
		super(owner, name, type, active);
	}
	
	public TownController(Entity owner) {
		this(owner, "TownController", 0, true);
	}
	
	@Override
	public void update(float delta){
		
	}

}
