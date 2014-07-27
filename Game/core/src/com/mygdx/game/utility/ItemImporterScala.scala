package com.mygdx.game.utility

import java.io.BufferedReader

import scala.actors.Actor

import com.badlogic.gdx.Gdx

object ItemImporterScala {

	def importItems() = {
		new ImporterActor().start;
	}

	class ImporterActor extends Actor {
		var finished : Boolean = false;
		
		def act() {
			val br : BufferedReader = new BufferedReader(Gdx.files.internal("config/items.cfg").reader());
			val strs = Stream.continually(br.readLine()).takeWhile(_ != null)
			
			var line : String = null;
			while((line = br.readLine()) != null){
				
			}

			var item : Item = new Item();

			System.out.println("Done reading");
		}
		
		def determineString(input : String, want : String) : Boolean = input match{
			case input if input == want => true
			case _ => false
		}
	}
}