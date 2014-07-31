package com.mygdx.game.screens

import com.badlogic.gdx.Screen
import com.mygdx.game.SpaceGame
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.Gdx
import com.mygdx.game.utility.GridScala
import com.mygdx.game.utility.ListHolder
import com.badlogic.gdx.graphics.GL20
import com.mygdx.game.entity.Entity
import java.util.Iterator
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.MathUtils
import com.mygdx.game.utility.Constants
import com.mygdx.game.entity.Tree
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.entity.TownHall
import com.mygdx.game.component.TownScala
import com.mygdx.game.component.TownScala
import com.mygdx.game.component.InventoryScala
import com.mygdx.game.utility.ItemBank
import com.mygdx.game.component.buildings.Building
import com.mygdx.game.utility.Item
import com.mygdx.game.component.InventoryScala
import com.mygdx.game.component.InventoryItemScala
import com.mygdx.game.component.buildings.ResidenceScala
import com.mygdx.game.component.GridEntity
import com.mygdx.game.entity.AICharacterEnt
import com.mygdx.game.component.character.AICharacterCompScala

class GameScreenScala(val game : SpaceGame) extends Screen{
	
	val batch = game.batch;
	val camera = new OrthographicCamera();
	
	camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	
	GridScala.initGrid(10,10,10,100);
	
	ListHolder.initListHolder();
	
	this.loadResources();
	this.loadTowns();
	this.loadCitizens();
	//this.initListeners();
	
	
	override def render(delta : Float) : Unit = {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.begin();

		var i = 0;
		
		for(i <- 0 until ListHolder.maxNumLevels){
			val iter : Iterator[Entity] = ListHolder.getEntityList(i).iterator();
	
			while (iter.hasNext()) {
				val ent = iter.next();
				//If entity is destroyed, remove it from the list.
				if (ent.isDestroyed()) {
					iter.remove();
				//Otherwise, update and draw it.
				}else{
					ent.update(delta); // Call normal update.
					ent.render(batch);
				}
			}
		}
		
		batch.end();
	}
	
	def loadResources() = {
		val img = new Texture("img/Tree.png");
		val i=0;
		
		for (i <- 0 until 5) {
			val randX : Float = MathUtils.random() * 800;
			val randY : Float= MathUtils.random() * 400;
			new Tree("Tree", new Vector2(randX, randY), 0, img, Constants.DRAWLEVEL_TREE);
		}
	}
	
	def loadTowns() = {
		// Create the town hall
		val town : Entity = new TownHall(new Vector2(200, 200), 0f, new Texture(
				Gdx.files.internal("img/Entity.png")), Constants.DRAWLEVEL_BUILDING);

		// Cache the Town script for now.
		val townScript : TownScala = town.getComponent(classOf[TownScala]);
		townScript.setMaxPopulation(10); // Set the max population.

		val stockpile : Entity = new Entity("Stockpile", new Vector2(300, 100), 0f,
				new Texture(Gdx.files.internal("img/StockPile.png")), Constants.DRAWLEVEL_BUILDING);

		stockpile.addComponent(new Building(stockpile, "Stockpile", Constants.BUILDING_STOCKPILE, false));
		
		val inv : InventoryScala[Item]  = new InventoryScala[Item](stockpile, 0, 200, true, true, true);
		val itemToAdd = ItemBank.getItem("Apple");
		inv.addToInventory(itemToAdd, 100, (item : InventoryItemScala[Item]) => itemToAdd.itemID == item.item.itemID);

		stockpile.addComponent(inv);
		townScript.addBuilding(stockpile.getComponent(classOf[Building]));
		
		val house : Entity = new Entity("House", new Vector2(100,100), 0f,
				new Texture(Gdx.files.internal("img/House.png")), Constants.DRAWLEVEL_BUILDING);
		
		house.entityType = Constants.ENTITY_BUILDING;
		house.entitySubType = Constants.BUILDING_RESIDENCE;
		
		val residence : ResidenceScala = new ResidenceScala(house, "House", Constants.BUILDING_RESIDENCE, false, 1); //Init the new residence component
		house.addComponent(residence); //Add the residence component to the entity.
		townScript.addBuilding(residence); //Add the residence to the list of buildings in the town.
		
		val comp = new GridEntity(house, "GridEntity", 0, false, Constants.GRID_STATIC);
		house.addComponent(comp).asInstanceOf[GridEntity]; //If the "asInstanceOf" is not there, it will try to cast it to 'Nothing' and will crash
	}
	
	def loadCitizens() = {
		val img = new Texture("img/AI.png"); //Load the textures
		val criteria = (e : Entity) => e.name == Constants.NAME_BUILDING_TOWNHALL;
		val townScript : TownScala = GridScala.getClosestEntityByCriteria(100, new Vector2(0,0), criteria).getComponent(classOf[TownScala]); //Find the town hall.
		
		var i=0;
		// Create some citizens.
		for (i <- 0 until 2) {
			val randX : Float = MathUtils.random() * 500 + 100;
			val randY : Float = MathUtils.random() * 500 + 100;

			val AI : Entity = new AICharacterEnt("AICharacter",
					new Vector2(randX, randY), 0f, img, Constants.DRAWLEVEL_CHARACTER);

			// Add the AI to the town.
			townScript.addToPopulation(AI.getComponent(classOf[AICharacterCompScala]));
		}
	}
	
	/*
	 private void initListeners(){
		Events.listen(EventClasses.TransferedItem.class, new EventInterfaces.TransferedItem() {
			@Override
			public void transferedItem(Entity entity, InventoryItem invItem) {
				System.out.println("Entity " + entity.name + " transfered " + invItem);
			}
		});
		
		Events.listen(EventClasses.CollectedResource.class, new EventInterfaces.CollectedResource() {
			@Override
			public void collectedResource(Entity entity, ResourceNode resource) {
				System.out.println("Entity " + entity.name + " collected resource " + resource.getResourceItem());
			}
		});
		
		Events.listen(EventClasses.ConsumedItem.class, new EventInterfaces.ConsumedItem() {
			
			@Override
			public void consumedItem(Entity entity, Item item) {
				System.out.println("Entity " + entity.name + " consumed " + item);
			}
		});
		
		Events.listen(EventClasses.ChangedJobGroup.class, new EventInterfaces.ChangedJobGroup() {
			
			@Override
			public void changedJobGroup(Entity entity, JobGroup group) {
				if(group != null)
					System.out.println("Entity "+entity.name+" changed job group to " + group.name);
				else
					System.out.println("Entity "+entity.name+" has no jobs queued.");
			}
		});
	}
	 */
	
	override def resize(width : Int, height : Int) {
		// TODO Auto-generated method stub

	}

	
	override def show() {
		// TODO Auto-generated method stub

	}

	override def hide() {
		// TODO Auto-generated method stub

	}

	override def pause() {
		// TODO Auto-generated method stub

	}

	override def resume() {
		// TODO Auto-generated method stub

	}

	override def dispose() {
		// TODO Auto-generated method stub

	}
}