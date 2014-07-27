package com.mygdx.game.jobgroups;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.component.AIJobController;
import com.mygdx.game.jobs.MoveTo;

public class Move extends JobGroup{
	
	/***
	 * A prebuilt JobGroup that has the following jobs in the following order:
	 * MoveTo.
	 * @param controller The AIJobController that this will belong to. This does not assign this job to
	 * the controller passed in. Instead, it's used to give information to the individual jobs such as the Entity's 
	 * location.
	 * @param name The name for the job.
	 * @param type The type of job indicated by an integer.
	 * @param pos A unique parameter for this JobGroup. The position for the MoveTo Job to move to.
	 */
	public Move(AIJobController controller, String name, int type, Vector2 pos) {
		super(controller, name, type);
		
		//Adds this job to the controller that was passed in.
		this.addJob(new MoveTo(name, type, null, pos));
	}

}
