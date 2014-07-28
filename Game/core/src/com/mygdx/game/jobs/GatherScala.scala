package com.mygdx.game.jobs

import com.mygdx.game.jobgroups.JobGroup
import com.mygdx.game.component.ResourceNode
import com.mygdx.game.component.InventoryScala
import com.mygdx.game.utility.Item
import com.mygdx.game.utility.Timer
import com.mygdx.game.component.InventoryItemScala

class GatherScala(group : JobGroup, name : String, groupType : Int, val resource : ResourceNode)
	extends Job(group, name, groupType){
	
	def this(group : JobGroup, resource : ResourceNode){
		this(group, "Gathering Resouce "+resource.getResourceItem().name, 0, resource);
	}
	
	val ownerInventory : InventoryScala[Item] = this.group.getController().getEntityOwner().getComponent(classOf[InventoryScala[Item]]);
	val timer : Timer = new Timer(resource.getTimeToCollect());
	
	override def update(delta : Float) : Unit = {
		timer.update(delta);
		
		//If the Entity we are trying to gather from is destroyed, cancel this job and job group.
		if(this.resource.isDestroyed()){
			this.finished = true; //Finish this job.
			this.group.setFinished(); //If the gather fails, set the whole group to finished. Nothing to gather = done.
			return;
		}
		
		//If the timer is done, collect resource, set this job to finished, and subtract 1 from the number of collectors.
		if(timer.done()){
			//Collect the Item and add it to the owner's inventory.
			val itemCollected : InventoryItemScala[Item] = this.resource.collectResourceItem(this.getJobGroup().getController().getEntityOwner(),
					this.resource.getAmountPerTick());
			
			val equality = (item : InventoryItemScala[Item]) => item.item.itemID == itemCollected.item.itemID;
			
			this.ownerInventory.addToInventory(itemCollected, equality); //Add it...
			
			//Set to finished and remove this collector from the resource.
			this.finished=true;
			this.resource.removeCollector();
		}
	}
	
	override def destroy() = {
		super.destroy();
		
	}

}