package com.mygdx.game.jobgroups;

import java.util.LinkedList;

import com.mygdx.game.component.AIJobController;
import com.mygdx.game.interfaces.Destroyable;
import com.mygdx.game.jobs.Job;

public abstract class JobGroup implements Destroyable{
	public String name;
	public int type;
	public boolean destroyed, unique;
	
	protected boolean finished;
	protected AIJobController controller;
	
	private LinkedList<Job> jobList;
	
	public JobGroup(AIJobController controller, String name, int type){
		this.name = name;
		this.type = type;
		this.controller = controller;
		
		this.jobList = new LinkedList<Job>();
	}
	
	public void update(float delta){
		if(jobList.size() > 0){
			Job job = this.jobList.getFirst(); //Caches the job
			if(job.isFinished()) //If the job is finished, pop it from the list and destroy it.
				this.jobList.pop().destroy();
			else 
				job.update(delta); //If it's not finished, run the update.
		}else{
			this.finished = true;
		}
	}
	
	public void setFinished(){
		this.finished = true;
	}
	
	public void addJob(Job job){
		this.controller.setFailed(false);
		this.jobList.add(job);
	}
	
	public boolean empty(){
		return this.jobList.size() < 1;
	}
	
	public boolean isFinished(){
		return this.finished;
	}
	
	public AIJobController getController(){
		return this.controller;
	}
	
	public boolean isUnique(){
		return this.unique;
	}
	
	@Override
	public void destroy(){
		this.controller = null;
		for(Job job : this.jobList)
			job.destroy();
		this.jobList.clear();
		
		this.destroyed = true;
	}
	
	public void setFailed(){
		this.finished = true;
		this.controller.setFailed(true);
	}
	
	public boolean isDestroyed(){
		return this.destroyed;
	}
}
