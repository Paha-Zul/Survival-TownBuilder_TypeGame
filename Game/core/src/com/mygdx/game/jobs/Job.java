package com.mygdx.game.jobs;

import com.mygdx.game.entity.Entity;
import com.mygdx.game.interfaces.Destroyable;
import com.mygdx.game.jobgroups.JobGroup;

public abstract class Job implements Destroyable {
	protected String name;
	protected int jobType;
	protected boolean finished = false, destroyed = false;
	protected Entity owner;

	JobGroup group;

	public Job(JobGroup group, String name, int jobType) {
		this.name = name;
		this.jobType = jobType;
		this.group = group;
		this.owner = group.getController().getEntityOwner();
	}

	public boolean isFinished() {
		return finished;
	}

	public abstract void update(float delta);

	@Override
	public void destroy() {
		this.owner = null;
		this.name = null;
		this.destroyed = true;
	}

	@Override
	public boolean isDestroyed() {
		return this.destroyed;
	}
	
	public JobGroup getJobGroup(){
		return this.group;
	}

}
