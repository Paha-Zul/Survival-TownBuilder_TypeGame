package com.mygdx.game.jobs;

import com.mygdx.game.component.CharacterNeeds;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.jobgroups.JobGroup;
import com.mygdx.game.utility.Timer;

public class SleepJob extends Job {
	int fatigueThreshold;
	CharacterNeeds needs;
	Timer tickTimer;

	public SleepJob(JobGroup group, String name, int jobType, CharacterNeeds needs, int fatigueThreshold) {
		super(group, name, jobType);
		
		this.needs = needs;
		this.fatigueThreshold = fatigueThreshold;
		this.tickTimer = new Timer(0.5f);
	}
	
	public SleepJob(JobGroup group, CharacterNeeds needs, int fatigueThreshold){
		this(group, "SleepJob", 0, needs, fatigueThreshold);
	}

	@Override
	public void update(float delta) {
		this.tickTimer.update(delta);
		
		//If we are below the 
		if(needs.currFatigue <= fatigueThreshold){
			this.finished = true;
			return;
		}
		
		if(this.tickTimer.done()){
			needs.currFatigue-=5;
			this.tickTimer.restart();
		}
	}

}
