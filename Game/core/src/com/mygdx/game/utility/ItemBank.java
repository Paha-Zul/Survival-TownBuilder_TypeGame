package com.mygdx.game.utility;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemBank {
	private static HashMap<String, Item> itemBank = new HashMap<String, Item>();
	private static HashMap<String, ArrayList<Item>> itemTypeList = new HashMap<String, ArrayList<Item>>();
	
	public ItemBank(){
		itemTypeList.put("all", new ArrayList<Item>());
	}

	public static void addItem(Item item) {
		if (itemBank.containsKey(item.name))
			throw new RuntimeException("Key already exists in ItemBank");

		itemBank.put(item.name, item);
	}

	public static Item getItem(String itemName){
		if(!itemBank.containsKey(itemName)) throw new RuntimeException("Item \""+itemName+"\" not in the ItemBank");
		return itemBank.get(itemName);
	}
	
	public static ArrayList<Item> getItemsAsList(){
		return new ArrayList<Item>(itemBank.values());
	}
	
	public static void calculateItemsList(){
		for(Item item : itemBank.values()){
			itemTypeList.get("all").add(item); //Add every item to the "all" category.
			
			//For each tag, add the item to the right categories.
			for(String tag : item.tags.split(",")){
				if(!itemTypeList.containsKey(tag)) //If the list doesn't exist, create it!
					itemTypeList.put(tag, new ArrayList<Item>());
				
				itemTypeList.get(tag).add(item); //Add the item to the list.
			}
		}
	}
	
	public static ArrayList<Item> getItemListByTag(String tag){
		return itemTypeList.get(tag);
	}
}
