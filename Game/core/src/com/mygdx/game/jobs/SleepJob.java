package com.mygdx.game.jobs;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.component.CharacterNeeds;
import com.mygdx.game.component.character.CharacterCompScala;
import com.mygdx.game.jobgroups.JobGroup;
import com.mygdx.game.utility.Timer;

public class SleepJob extends Job {
	int fatigueThreshold;
	CharacterNeeds needs;
	CharacterCompScala character;
	Timer tickTimer;
	
	int id = MathUtils.random(100);

	public SleepJob(JobGroup group, String name, int jobType, CharacterNeeds needs, CharacterCompScala character, int fatigueThreshold) {
		super(group, name, jobType);
		
		this.needs = needs;
		this.fatigueThreshold = fatigueThreshold;
		this.tickTimer = new Timer(0.5f);
		this.character = character;
	}
	
	public SleepJob(JobGroup group, CharacterNeeds needs, CharacterCompScala character, int fatigueThreshold){
		this(group, "SleepJob", 0, needs, character, fatigueThreshold);
	}

	@Override
	public void update(float delta) {
		this.tickTimer.update(delta);
		this.character.setSleeping(true);
		
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
