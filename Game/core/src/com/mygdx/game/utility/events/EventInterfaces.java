package com.mygdx.game.utility.events;

import com.mygdx.game.component.Inventory.InventoryItem;
import com.mygdx.game.component.ResourceNode;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.jobgroups.JobGroup;
import com.mygdx.game.utility.Item;

public class EventInterfaces {

	public static interface GameEvent<L> {
		public void notify(final L listener);
	}

	public static interface TransferedItem {
		public void transferedItem(Entity entity, InventoryItem invItem);
	}
	
	public static interface CollectedResource {
		public void collectedResource(Entity entity, ResourceNode resource);
	}
	
	public static interface ConsumedItem {
		public void consumedItem(Entity entity, Item item);
	}
	
	public static interface ChangedJobGroup{
		public void changedJobGroup(Entity entity, JobGroup group);
	}
	
	public static interface Building{
		public void changedJobGroup(Entity entity, JobGroup group);
	}

}
