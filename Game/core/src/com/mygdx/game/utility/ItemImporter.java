package com.mygdx.game.utility;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;

public class ItemImporter {
		
	public static void importItems(){
		BufferedReader reader = null;

		reader = new BufferedReader( Gdx.files.internal("config/items.cfg").reader());
        
		Item item  = null;
        String line;
        String[] split;
        try {
			while ((line = reader.readLine()) != null) {
				if(is(line, ""))
					continue;
				else if(line.equals("{")){
					item = new Item();
					continue;
				}else if(line.equals("}")){
					ItemBank.addItem(item);
					item = null;
					continue;
				}
				
				split = line.split(":"); //Split the line into parts by the ':'
				String identifier = split[0]; //Cache first part for easy access
				String value = split[1]; //Cache second part for easy access.
				
				if(is(identifier, "name")){ //If name, record the name.
					item.name = value.trim();
				}else if(is(identifier, "weight")){ //If weight, record the weight.
					item.weight = Integer.parseInt(value.trim());
				}else if(is(identifier, "ID")){ //Record itemID
					item.itemID = Integer.parseInt(value.trim());
				}else if(is(identifier, "tags")){ //Record itemType
					item.tags = value.trim();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Helper function to make things cleaner
	private static boolean is(String input, String want){
		return input.trim().equals(want);
	}

}
