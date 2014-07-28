package com.mygdx.game.jobs

import com.mygdx.game.component.InventoryScala
import com.mygdx.game.jobgroups.JobGroup
import com.mygdx.game.utility.Item
import com.mygdx.game.utility.events.EventClasses
import com.mygdx.game.utility.Events
import com.mygdx.game.component.InventoryItemScala

class TransferItemScala(group : JobGroup, name : String, jobType : Int, val fromInv : InventoryScala[Item], 
		val toInv : InventoryScala[Item], val item : Item, val quantity : Int, val transferAll : Boolean) 
		extends Job(group, name, jobType){
	
	def this(group : JobGroup, fromInv : InventoryScala[Item], 
		 toInv : InventoryScala[Item], item : Item,  quantity : Int, transferAll : Boolean){
		
		this(group, "TransferItem "+item.name, 0, fromInv, toInv, item, quantity, transferAll);
	}
	
	override def update(delta : Float) : Unit = {
		//Get (attempt to get?) the Item.
		var invItem : InventoryItemScala[Item] = null;
		val test = (itemInInv : InventoryItemScala[Item]) => item.itemID == itemInInv.item.itemID;
		
		if(this.transferAll) invItem = this.fromInv.removeItemFromInventory(test);
		else invItem = this.fromInv.removeItemFromInventory(this.quantity, (itemInInv : InventoryItemScala[Item]) => itemInInv.item.itemID == item.itemID);
		
		//If the InventoryItem is null (meaning it didn't exist in the Inventory), set this job to finished and return.
		if(invItem == null){
			this.finished = true;
			return;
		}
		
		//Call the event...
		Events.notify(new EventClasses.TransferedItem(this.getJobGroup().getController().getEntityOwner(), invItem));
		
		//Get the item from the inventory and give it to the "toInv" inventory.
		this.toInv.addToInventory(item, quantity, test);
		this.finished = true;
	}
}