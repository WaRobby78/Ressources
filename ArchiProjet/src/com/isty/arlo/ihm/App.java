package com.isty.arlo.ihm;

//import com.isty.arlo.db.DatabaseManager;

public class App {
	public static void main(String[] args) throws ClassNotFoundException {
		// Décommenter pour créer une database si vous en avez pas
		//DatabaseManager.createTable("personne");
		//DatabaseManager.createTable("salle");
		//DatabaseManager.createTable("creneau");
		//DatabaseManager.createTable("reservation");
		
		new Gui().createFrame();
		
	  }
}
