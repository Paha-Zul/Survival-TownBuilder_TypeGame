package com.mygdx.game.component.buildings;

import java.util.ArrayList;

import com.mygdx.game.entity.Entity;
import com.mygdx.game.utility.Constants;

public class Residence extends Building {
	private int space;
	private ArrayList<Entity> residentList;

	public Residence(Entity owner, String name, int type, boolean active,
			int space) {
		super(owner, name, type, active);
		
		this.residentList = new ArrayList<Entity>();
		
	}

	public Residence(Entity owner, int type, int space) {
		this(owner, "GenericResidence", type, false, space);
		
	}
	
	public Residence(Entity owner, int space) {
		this(owner, "GenericResidence", Constants.BUILDING_RESIDENCE, false, space);
	}

}
