package com.mygdx.game.component.buildings

import com.mygdx.game.component.InventoryItemScala
import com.mygdx.game.component.InventoryScala
import com.mygdx.game.component.character.CharacterComp
import com.mygdx.game.entity.Entity

class ResidenceScala(owner : Entity, name : String, buildType : Int, active : Boolean, var space : Int) 
	extends Building(owner, name, buildType, active) {

	var residentList : InventoryScala[CharacterComp] = new InventoryScala[CharacterComp](owner, name, 0, false, space, 99999, false, false, false);
	
	def addToResidence(char : CharacterComp) : Boolean = {
		return residentList.addToInventory(char, 1, (char1 : InventoryItemScala[CharacterComp]) => true);
	}
	
	def isFull() : Boolean = {
		return this.residentList.inventory.size  >= space;
	}
}