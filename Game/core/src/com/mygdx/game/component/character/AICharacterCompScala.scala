package com.mygdx.game.component.character

import com.mygdx.game.component.AIJobController
import com.mygdx.game.component.CharacterNeeds
import com.mygdx.game.component.InventoryItemScala
import com.mygdx.game.component.InventoryScala
import com.mygdx.game.component.TownScala
import com.mygdx.game.component.buildings.Building
import com.mygdx.game.component.buildings.ResidenceScala
import com.mygdx.game.component.buildings.ResidenceScala
import com.mygdx.game.component.buildings.ResidenceScala
import com.mygdx.game.component.buildings.ResidenceScala
import com.mygdx.game.entity.Entity
import com.mygdx.game.jobgroups.JobGroup
import com.mygdx.game.jobgroups.RetrieveItem
import com.mygdx.game.utility.Constants
import com.mygdx.game.utility.Item
import com.mygdx.game.utility.Timer
import com.mygdx.game.jobgroups.GatherResourceScala
import com.mygdx.game.jobgroups.SleepScala

class AICharacterCompScala(owner : Entity, name : String, charType : Int, active : Boolean)
	extends CharacterComp(owner, name, charType, active) {
	
	def this(owner : Entity) = {
		this(owner, "GenericEntity", 0, true);
	}
	
	var jobController : AIJobController = new AIJobController(owner, name, 0, active);
	var town : TownScala = _;
	var inventory : InventoryScala[Item] = new InventoryScala[Item](owner, "InventoryScala", 0, false, 0, 99999, true, true, true);
	val callback  = (AI : AICharacterCompScala) => AI.sleeping = false;

	var sleeping : Boolean = false;

	var needs : CharacterNeeds = owner.addComponent(new CharacterNeeds(owner, "CharNeeds", 0, false));

	// Timers
	var jobDelay : Timer = new Timer(5);
	var eatTimer : Timer = new Timer(5);
	var sleepTimer : Timer = new Timer(1);

	override def update(delta : Float) : Unit = {
		this.jobDelay.update(delta);
		this.eatTimer.update(delta);
		this.sleepTimer.update(delta);

		//If the sleeping need is above 80, assign a sleep job.
		if(this.needs.currFatigue >= 80 && !this.sleeping){
			//If my home is null, try to find a home...
			if(home == null){
				val criteria = (building : Building) => !(building.asInstanceOf[ResidenceScala]).isFull();
				this.home = this.town.getBuildingByCriteria(Constants.BUILDING_RESIDENCE, criteria).asInstanceOf[ResidenceScala];
			//Otherwise, sleep in my home.
			}else{
				this.jobController.addJobGroup(new SleepScala(jobController, home, needs, 10, this.callback));
				this.sleeping = true;
			}
		}
		
		//If we are already sleeping, do nothing
		if(this.sleeping)
			return;
		
		//If the sleepTimer is done, add some fatigue
		if(this.sleepTimer.done()){
			needs.currFatigue+=10;
			this.sleepTimer.restart();
		}

		// If the eat timer is done.. try to eat and restart.
		if (this.eatTimer.done()) {
			eat();
			this.eatTimer.restart();
		}

		// If there are no jobs and the timer is done, find a new job.
		if (jobController.hasNoJobs() && this.jobDelay.done()) {
			if (this.jobDelay.done()) {
				this.jobController.addJobGroup(new GatherResourceScala(
						this.jobController, "WoodLog", town));
			}
		}

		// If the finding a job has failed, wait some time...
		if (this.jobController.hasFailed()) {
			this.jobDelay.restart(5);
		}
	}
	
	def eat() : Unit = {
		val tag : String= "food";
		val getItemWithTag = (item : InventoryItemScala[Item]) => item.item.hasTag(tag);
		var itemToGet : InventoryItemScala[Item] = null;

		//Attempt to consume a unit of food.
		val itemToEat : InventoryItemScala[Item] = this.inventory.removeItemFromInventory(1, getItemWithTag);

		// If the itemToEat is null, it means we have no item in our inventory
		// to eat. We must find some from a nearby stockpile or source.
		if (itemToEat == null) {
			val getItemWithTag = (item : InventoryItemScala[Item]) => item.item.hasTag(tag) && item.quantity >= 1;

			val list = this.town.getBuildingList(Constants.BUILDING_STOCKPILE);
			var i=0;
			
			// Check each stockpile's supply for a food item.
			for (i <- 0 until list.size()) {
				val building = list.get(i);
				
				// Get the inventory
				val buildingInv : InventoryScala[Item] = building.getEntityOwner().getComponent(classOf[InventoryScala[Item]]);

				// If there is an item that has the "food" tag...
				if ((itemToGet = buildingInv.hasItemInInventory(getItemWithTag)) != null) { 
					//Add a jobgroup to retrieve the item.
					val getFood : JobGroup = new RetrieveItem(this.jobController,
							"GetFood", 0, itemToGet.item, 5, buildingInv);
					getFood.unique = true;
					
					this.jobController.addJobGroup(getFood); // Add the jobgroup.
					return;
				}
			}
		} else {
			itemToEat.item.consumeItem(this.getEntityOwner());
		}
	}
	
	def assignTownOwner(town : TownScala) = {
		this.town = town;
	}
	
	override def destroy() = {
		this.jobController = null;
		this.eatTimer = null;
		this.jobDelay = null;
		this.town = null;
		this.inventory = null;
	}
	
	/*
	 public AICharacterComp(Entity owner, String name, int type, boolean active) {
		super(owner, name, type, active);

		this.inventory = this.getEntityOwner().getComponent(Inventory.class);
		this.jobController = this.owner.getComponent(AIJobController.class);
		this.jobDelay = new Timer(0);

		this.eatTimer = new Timer(5 + MathUtils.random());
		this.sleepTimer = new Timer(1);

		this.needs = this.owner.addComponent(new CharacterNeeds(this
				.getEntityOwner(), "Needs", 0, false));

		this.needs.currFatigue = 100;
		
		this.callback = (AI) -> AI.sleeping = false;
	}

	public AICharacterComp(Entity owner) {
		this(owner, "AICitizen", 0, true);

	}

	public AICharacterComp(Entity owner, Inventory inventory) {
		this(owner, "AICitizen", 0, true);

		this.inventory = inventory;
	}

	@Override
	public void update(float delta) {
		//Update all timers
		this.jobDelay.update(delta);
		this.eatTimer.update(delta);
		this.sleepTimer.update(delta);

		//If the sleeping need is above 80, assign a sleep job.
		if(this.needs.currFatigue >= 80 && !this.sleeping){
			//If my home is null, try to find a home...
			if(home == null){
				BuildingCriteria criteria = (building) -> !((ResidenceScala)building).isFull();
				this.home = (ResidenceScala)this.town.getBuildingByCriteria(Constants.BUILDING_RESIDENCE, criteria);
			//Otherwise, sleep in my home.
			}else{
				this.jobController.addJobGroup(new Sleep(jobController, home, needs, 10, this.callback));
				this.sleeping = true;
			}
		}
		
		//If we are already sleeping, do nothing
		if(this.sleeping)
			return;
		
		//If the sleepTimer is done, add some fatigue
		if(this.sleepTimer.done()){
			needs.currFatigue+=10;
			this.sleepTimer.restart();
		}

		// If the eat timer is done.. try to eat and restart.
		if (this.eatTimer.done()) {
			eat();
			this.eatTimer.restart();
		}

		// If there are no jobs and the timer is done, find a new job.
		if (jobController.hasNoJobs() && this.jobDelay.done()) {
			if (this.jobDelay.done()) {
				this.jobController.addJobGroup(new GatherResource(
						this.jobController, "WoodLog", town));
			}
		}

		// If the finding a job has failed, wait some time...
		if (this.jobController.hasFailed()) {
			this.jobDelay.restart(5);
		}
	}

	public void assignTownOwner(Town town) {
		this.town = town;
	}

	public void eat() {
		String tag = "food";
		ItemCriteria getItemWithTag = (item) -> item.item.hasTag(tag);
		Item itemToGet = null;

		// This consumes the item if it is found.
		InventoryItem itemToEat = this.inventory.removeFirstItemFromInventory(
				getItemWithTag, 1);

		// If the itemToEat is null, it means we have no item in our inventory
		// to eat. We must find some from a nearby stockpile or source.
		if (itemToEat == null) {
			getItemWithTag = (item) -> item.item.hasTag(tag)
					&& item.quantity >= 1;

			// Check each stockpile's supply for a food item.
			for (Building building : this.town.getBuildingList(Constants.BUILDING_STOCKPILE)) {
				// Get the inventory
				Inventory buildingInv = building.getEntityOwner().getComponent(
						Inventory.class);

				// If there is an item that has the "food" tag...
				if ((itemToGet = buildingInv.hasInventoryItem(getItemWithTag)) != null) { 
					//Add a jobgroup to retrieve the item.
					JobGroup getFood = new RetrieveItem(this.jobController,
							"GetFood", 0, itemToGet, 5, buildingInv);
					getFood.unique = true;
					this.jobController.addJobGroup(getFood); // Add the jobgroup.
					return;
				}
			}
		} else {
			itemToEat.item.consumeItem(this.getEntityOwner());
		}
	}

	public static interface ItemCriteria {
		boolean withinCriteria(InventoryItem invItem);
	}

	@Override
	public void destroy() {
		this.jobController = null;
		this.eatTimer = null;
		this.jobDelay = null;
		this.town = null;
		this.inventory = null;
	}
	 
	 */

}