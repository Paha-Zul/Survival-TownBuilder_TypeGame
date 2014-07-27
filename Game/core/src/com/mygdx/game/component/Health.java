package com.mygdx.game.component;

import com.mygdx.game.entity.Entity;

public class Health extends Component{
	private float maxHealth, currentHealth;
	
	public Health(Entity owner, String name, int type, boolean active, float maxHealth){
		super(owner, name, type, false);
		
		this.maxHealth = maxHealth;
		this.currentHealth = this.maxHealth;
	}
	
	public Health(Entity owner, float maxHealth){
		this(owner, "Health", 0, false, maxHealth);
		
	}
	
	public float getMaxHealth(){
		return this.maxHealth;
	}
	
	public float getCurrentHEalth(){
		return this.currentHealth;
	}
	
	public float getPercentHealth(){
		return this.currentHealth/this.maxHealth;
	}
	
	/**
	 * Adds an amount of health. This can be either positive or negative.
	 * @param amt The amount of health to add (positive or negative).
	 */
	public void addHealth(float amt){
		this.currentHealth+=amt;
		if(this.currentHealth > this.maxHealth) 
			this.currentHealth = this.maxHealth;
	}
}
