package com.mygdx.game.component;

import com.mygdx.game.entity.Entity;
import com.mygdx.game.utility.Item;

public class ItemComponent extends Component {
	public Item item;

	public ItemComponent(Entity owner, String name, int type, boolean active, Item item) {
		super(owner, name, type, active);
		
		this.item = item;
	}
	
	public ItemComponent(Entity owner, Item item) {
		this(owner, "Item", 0, false, item);
		
	}

}
