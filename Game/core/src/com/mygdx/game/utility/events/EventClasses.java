package com.mygdx.game.utility.events;

import com.mygdx.game.component.InventoryItemScala;
import com.mygdx.game.component.ResourceNode;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.jobgroups.JobGroup;
import com.mygdx.game.utility.Item;

public class EventClasses {

	public static class TransferedItem implements EventInterfaces.GameEvent<EventInterfaces.TransferedItem>{
		Entity entity;
		InventoryItemScala<Item> invItem;
		
		public TransferedItem(Entity entity, InventoryItemScala<Item> invItem){
			this.entity = entity;
			this.invItem = invItem;
		}

		@Override
		public void notify(EventInterfaces.TransferedItem listener) {
			listener.transferedItem(entity, invItem);
		}
	}
	
	public static class CollectedResource implements EventInterfaces.GameEvent<EventInterfaces.CollectedResource>{
		Entity entity;
		ResourceNode resource;
		
		public CollectedResource(Entity entity, ResourceNode resource){
			this.entity = entity;
			this.resource = resource;
		}

		@Override
		public void notify(EventInterfaces.CollectedResource listener) {
			listener.collectedResource(entity, resource);
		}
	}
	
	public static class ConsumedItem implements EventInterfaces.GameEvent<EventInterfaces.ConsumedItem>{
		Entity entity;
		Item item;
		
		public ConsumedItem(Entity entity, Item item){
			this.entity = entity;
			this.item = item;
		}

		@Override
		public void notify(EventInterfaces.ConsumedItem listener) {
			listener.consumedItem(entity, item);
		}
	}
	
	public static class ChangedJobGroup implements EventInterfaces.GameEvent<EventInterfaces.ChangedJobGroup>{
		JobGroup group;
		Entity entity;
		
		public ChangedJobGroup(Entity entity, JobGroup group){
			this.entity = entity;
			this.group = group;
		}

		@Override
		public void notify(EventInterfaces.ChangedJobGroup listener) {
			listener.changedJobGroup(entity, group);
		}
	}

}
