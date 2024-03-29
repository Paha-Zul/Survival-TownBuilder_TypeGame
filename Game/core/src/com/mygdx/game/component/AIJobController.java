package com.mygdx.game.component;

import java.util.LinkedList;

import com.mygdx.game.component.Inventory.InventoryItem;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.jobgroups.JobGroup;
import com.mygdx.game.utility.Events;
import com.mygdx.game.utility.ItemBank;
import com.mygdx.game.utility.events.EventClasses;

public class AIJobController extends Component{
	private LinkedList<JobGroup> jobGroupList;
	private boolean hasFailed = false;
	
	public AIJobController(Entity owner, String name, int type, boolean active) {
		super(owner, name, type, active);
		
		jobGroupList = new LinkedList<JobGroup>();
		
	}
	
	public AIJobController(Entity owner) {
		this(owner, "AIJobController", 0, true);
	}

	@Override
	public void update(float delta){
		//If there is something in the JobGroup List
		if(jobGroupList.size() > 0){
			JobGroup group = jobGroupList.getFirst(); //Cache the group.
			if(group.isFinished()){ //If the group is finished, pop it off the list and destroy it.
				advanceJobs();
			}
			else group.update(delta); //If not finished, update it.
		//If there is nothing in the list...
		}
	}
	
	private void advanceJobs(){
		jobGroupList.pop().destroy(); //If finished, pop it off the list.
		//Events.notify(new EventClasses.ChangedJobGroup(this.getEntityOwner(), jobGroupList.getFirst())); <- Can't be null apparently
	}
	
	public void addJobGroup(JobGroup group){
		if(group.isUnique())
			if(this.hasJobGroup(group.name))
				return;
		
		this.jobGroupList.add(group);
	}
	
	public void addJobGroupToFront(JobGroup group){
		if(group.isUnique())
			if(this.hasJobGroup(group.name))
				return;
		
		this.jobGroupList.addFirst(group);
	}
	
	public JobGroup findGroupByName(String name){
		for(JobGroup group : jobGroupList) //Foreach group
			if(group.name.equals(name)) return group; //If the name matches, return it.
		return null; //No matches found, return null;
	}
	
	/**
	 * Checks the list of jobs for a JobGroup with a certain name.
	 * @param name The name of the JobGroup trying to be found.
	 * @return True if there is a JobGroup with "name" name, false otherwise.
	 */
	public boolean hasJobGroup(String name){
		for(JobGroup group : this.jobGroupList)
			if(group.name.equals(name))
				return true;
		
		return false;
	}
	
	/**
	 * Checks the list of jobs for a JobGroup with a certain type
	 * @param type The type of JobGroup to be found.
	 * @return True if there is a JobGroup with "type" type, false otherwise.
	 */
	public boolean hasJobGroup(int type){
		for(JobGroup group : this.jobGroupList)
			if(group.type == type)
				return true;
		
		return false;
	}
	
	public boolean hasNoJobs(){
		return this.jobGroupList.size() == 0;
	}
	
	public boolean hasFailed(){
		return this.hasFailed;
	}
	
	public void setFailed(boolean failed){
		this.hasFailed = failed;
	}
	
}
