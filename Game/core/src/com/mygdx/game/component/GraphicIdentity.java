package com.mygdx.game.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Entity;

public class GraphicIdentity extends Component{
	public Sprite sprite;
	
	public GraphicIdentity(Entity owner, String name, int type, boolean active, Texture image){
		super(owner, name, type, active);
		
		this.sprite = new Sprite(image);
		Vector2 pos = this.owner.transform.getWorldPosition(); //Cache the owner's position.
		this.sprite.setPosition(pos.x - this.sprite.getWidth()/2, pos.y - this.sprite.getHeight()/2);
		this.sprite.setCenter(-this.sprite.getWidth()/2, -this.sprite.getHeight()/2);
		this.sprite.setOrigin(-this.sprite.getWidth()/2, -this.sprite.getHeight()/2);
	}
	
	public GraphicIdentity(Entity owner, Texture image){
		this(owner, "Identity", 0, true, image);
	}
	
	public void render(SpriteBatch batch){
		Vector2 pos = this.owner.transform.getWorldPosition(); //Cache the owner's position.
		
		this.sprite.setPosition(pos.x - this.sprite.getWidth()/2, pos.y - this.sprite.getHeight()/2);
		this.sprite.draw(batch);
	}
}
