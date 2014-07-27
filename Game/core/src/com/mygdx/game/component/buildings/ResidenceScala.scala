package com.mygdx.game.component.buildings

import com.mygdx.game.entity.Entity
import com.mygdx.game.component.InventoryScala
import com.mygdx.game.component.Component
import com.mygdx.game.component.character.CharacterComp;

class ResidenceScala(owner : Entity, name : String, buildType : Int, active : Boolean, var space : Int) 
	extends Building(owner, name, buildType, active) {

	var residentList : InventoryScala[CharacterComp] = new InventoryScala[CharacterComp](owner, name, 0, false, space, false, false, false);
	
	def addToResidence(char : CharacterComp) : Boolean = {
		return residentList.addToInventory(char, 1, (char1, char2) => true);
	}
	
	def isFull() : Boolean = {
		return this.residentList.inventory.size  >= space;
	}
}