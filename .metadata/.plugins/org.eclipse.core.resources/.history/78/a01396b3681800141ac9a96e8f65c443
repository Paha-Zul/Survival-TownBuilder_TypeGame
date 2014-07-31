package com.mygdx.game.utility;

public class Timer {
	private float timer, timeToPass;
	
	/**
	 * Initializes a timer with the time to pass in seconds. 
	 * Update must be called in order to update the timer.
	 * @param timeToPass The time to pass.
	 */
	public Timer(float timeToPass){
		this.timeToPass = timeToPass;
		this.timer = 0;
	}
	
	public void update(float delta){
		timer+=delta;
	}
	
	public boolean done(){
		if(timer >= timeToPass)
			return true;
		return false;
	}
	
	public void restart(){
		timer=0;
	}
	
	public void restart(float timeToPass){
		timer = 0;
		this.timeToPass = timeToPass;
	}
}
