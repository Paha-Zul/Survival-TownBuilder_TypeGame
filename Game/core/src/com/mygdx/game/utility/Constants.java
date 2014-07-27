package com.mygdx.game.utility;

public class Constants {
	//Grid Entity types
	public static final int GRID_STATIC=0;
	public static final int GRID_MOVING=1;
	
	//Entity types
	public static final int ENTITY_DEFAULT=0;
	public static final int ENTITY_CHARACTER=1;
	public static final int ENTITY_BUILDING=2;
	public static final int ENTITY_RESOURCE=3;
	
	
	//Item types
	public static final int ITEM_WOODLOG=0;
	public static final int ITEM_WOODPLANK=1;
	
	//Building types
	public static final int BUILDING_TOWNHALL=0;
	public static final int BUILDING_STOCKPILE=1;
	public static final int BUILDING_RESIDENCE=2;
	public static final int BUILDING_FARM=3;
	
	//Names
	public static final String NAME_BUILDING_TOWNHALL = "TownHall";
	
	//Common draw levels
	public static final int DRAWLEVEL_BUILDING = 10; //In the middle.
	public static final int DRAWLEVEL_TREE = 15; //Drawn on top of most things.
	public static final int DRAWLEVEL_CHARACTER = 5; //Drawn under most things.
	
}
