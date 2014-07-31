package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.component.GridEntity;
import com.mygdx.game.component.ResourceNode;
import com.mygdx.game.utility.Constants;
import com.mygdx.game.utility.ItemBank;

/**
 * A Prebuilt Entity component. Contains the following components:
 * ResourceNode
 * 
 * The health component will be used for resource extraction.
 */
public class Tree extends Entity{
	public Tree(String name,Vector2 position, float rotation, Texture graphic, int drawLevel) {
		super(name, position, rotation, graphic, drawLevel);
		
		this.entityType = Constants.ENTITY_RESOURCE;
		
		this.addComponent(new ResourceNode(this, 100, 2, 2f, 2, ItemBank.getItem("WoodLog")));
		this.addComponent(new GridEntity(this, "GridEntity_Tree", 0, false, Constants.GRID_STATIC));
		
		this.entityType = Constants.ENTITY_RESOURCE;
	}
}
