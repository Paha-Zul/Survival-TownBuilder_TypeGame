package com.mygdx.game.jobgroups

import com.mygdx.game.component.AIJobController
import com.mygdx.game.component.CharacterNeeds
import com.mygdx.game.component.buildings.ResidenceScala
import com.mygdx.game.entity.Entity
import com.mygdx.game.jobs.MoveTo
import com.mygdx.game.jobs.SleepJob
import com.mygdx.game.component.character.AICharacterCompScala
import com.mygdx.game.component.Component
import com.mygdx.game.component.character.CharacterCompScala

class SleepScala(controller : AIJobController, name : String, groupType : Int, home : ResidenceScala, needs : CharacterNeeds,
		threshold : Int, character : CharacterCompScala, val callback : (AICharacterCompScala) => Unit) 
		extends JobGroup(controller, name, groupType) {
	
	def this(controller : AIJobController, home : ResidenceScala, needs : CharacterNeeds, threshold : Int, character : CharacterCompScala, callback : (AICharacterCompScala) => Unit){
		this(controller, "SleepScala", 0, home, needs, threshold, character, callback);
	}
	
	this.unique = true;
	
	//If there is no home, don't move to it! We will sleep right where we are standing...
	if(home != null)this.addJob(new MoveTo(this, home.getEntityOwner()));
	this.addJob(new SleepJob(this, needs, character, threshold));
	
	override def destroy() = {
		println("Done sleeping!");
		this.callback((this.getController().getEntityOwner().getComponent(classOf[AICharacterCompScala]))) 
	}
		
}