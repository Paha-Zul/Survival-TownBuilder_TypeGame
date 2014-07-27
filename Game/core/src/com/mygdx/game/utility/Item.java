package com.mygdx.game.utility;

import com.mygdx.game.entity.Entity;
import com.mygdx.game.utility.events.EventClasses;

public class Item {
	public String name, tags;
	public int weight, itemID;
	public float baseWorth;
	
	public Item(String name) {
		this.name = name;
	}
	
	public Item() {
		this("GenericItem");
	}
	
	/**
	 * Checks if this item has a tag that matches the tag passed in.
	 * @param tag The tag to check for, ie: "food" or "raw".. etc.
	 * @return True if the tag was found, false otherwise.
	 */
	public boolean hasTag(String tag){
		String[] splitTags = this.tags.split(",");
		for(String itemTag : splitTags)
			if(tag.equals(itemTag.trim()))
				return true;
		
		return false;
	}
	
	/**
	 * Consumes the item, applying an effect if the item has one.
	 * @param entity The Entity that is consuming this Item.
	 */
	public void consumeItem(Entity entity){
		Events.notify(new EventClasses.ConsumedItem(entity, this));
	}
	
	@Override 
	public String toString(){
		return this.name;
	}
	
}
