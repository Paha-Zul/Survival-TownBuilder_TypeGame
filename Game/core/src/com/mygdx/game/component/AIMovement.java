package com.mygdx.game.component;

import com.mygdx.game.entity.Entity;

public class AIMovement extends Component{
	public float maxSpeed;
	public float currSpeed;

	public AIMovement(Entity owner, String name, int type, boolean active, float maxSpeed) {
		super(owner, name, type, active);
		
		this.maxSpeed = maxSpeed;
	}
	
	public AIMovement(Entity owner, float maxSpeed) {
		this(owner, "AIMovement", 0, true, maxSpeed);
		
		this.maxSpeed = maxSpeed;
	}
	
	@Override
	public void update(float delta){
		
	}

}
