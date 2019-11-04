package com.isty.arlo.db;

import com.isty.arlo.domain.Creneau;
import com.isty.arlo.domain.Personne;
import com.isty.arlo.domain.Reservation;
import com.isty.arlo.domain.Salle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public interface RessourcesInterface {
	
	public static String dbConnection = "jdbc:sqlite:database.db";
	
	//CREATION TABLE BASE DE DONNEES
	//Renvoie true si la table a bien été créée
	public static boolean createTable(String tableName) {
		try { Class.forName("org.sqlite.JDBC"); } catch (ClassNotFoundException e1) {e1.printStackTrace(); }
		Connection connection = null;
		try{
		   connection = DriverManager.getConnection(dbConnection);
		   Statement statement = connection.createStatement();
		   
		   if(tableName.equals("personne")){
			   statement.executeUpdate("DROP TABLE IF EXISTS personne");
			   statement.executeUpdate("CREATE TABLE personne (id STRING, nom STRING, prenom STRING, job STRING)");
		   }
		   else if (tableName.equals("salle")){
			   statement.executeUpdate("DROP TABLE IF EXISTS salle");
			   statement.executeUpdate("CREATE TABLE salle (id STRING, nom STRING UNIQUE)");
		   }
		   else if (tableName.equals("creneau")){
			   statement.executeUpdate("DROP TABLE IF EXISTS creneau");
			   statement.executeUpdate("CREATE TABLE creneau (id STRING, start DATE, end DATE, UNIQUE(start, end))");
		   }
			else if (tableName.equals("reservation")){
				statement.executeUpdate("DROP TABLE IF EXISTS reservation");
				statement.executeUpdate("CREATE TABLE reservation (id STRING, idPersonne INTEGER, idSalle INTEGER, idCreneau INTEGER, UNIQUE(idPersonne, idSalle, idCreneau))");
			}
		   
		}
		catch(SQLException e){ System.err.println(e.getMessage()); return false; }
		finally {         
	        try{
	        	if(connection != null)
	        		connection.close();
	        }
	        catch(SQLException e){ System.err.println(e); return false; }
        }
		return true;
	}
	
	//CREATION UN OBJET
	//Renvoie true si la personne a bien été insérée dans la table
	public static boolean insertEntite(Object objet, String tableName) {
			
		try { Class.forName("org.sqlite.JDBC"); } catch (ClassNotFoundException e1) {e1.printStackTrace(); }
		Connection connection = null;
		try{
		   connection = DriverManager.getConnection(dbConnection);
		   Statement statement = connection.createStatement();
		   if(tableName.equals("personne")) {
			   Personne personne = (Personne) objet;
			   statement.executeUpdate("INSERT INTO personne(id, nom, prenom, job) values('"+personne.getId()+"', '"+personne.getNom()+"', '"+personne.getPrenom()+"', '"+personne.getJob()+"')");
		   }
		   else if(tableName.equals("salle")) {
			   Salle salle = (Salle) objet;
			   statement.executeUpdate("INSERT INTO salle(id, nom) values('"+salle.getId()+"', '"+salle.getNom()+"')");
		   }
		   else if(tableName.equals("creneau")) {
			   Creneau creneau = (Creneau) objet;
			   String start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(creneau.getStart());
			   String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(creneau.getEnd());
			   statement.executeUpdate("INSERT INTO creneau(id, start, end) values('"+creneau.getId()+"', '"+start+"', '"+end+"')");
		   }
		   else if(tableName.equals("reservation")) {
			   Reservation reservation = (Reservation) objet;
			   statement.executeUpdate("INSERT INTO reservation(id, idPersonne, idSalle, idCreneau) "
			   		+ "values('"+reservation.getId()+"', '"+reservation.getPersonne().getId()+"', '"+reservation.getSalle().getId()+"', '"+reservation.getCreneau().getId()+"')");
		   }
		   
		}
		catch(SQLException e){  System.err.println(e.getMessage()); return false; }
		finally {         
	        try{
	        	if(connection != null)
	        		connection.close();
	        }
	        catch(SQLException e){ System.err.println(e); return false; }
        }
		return true;
	}
	
	//SUPPRESSION UN OBJET
	//Renvoie true si la personne a bien été supprimée dans la table
	public static boolean deleteEntite(String id, String tableName) {
		
		try { Class.forName("org.sqlite.JDBC"); } catch (ClassNotFoundException e1) {e1.printStackTrace(); }
		Connection connection = null;
		try{
		   connection = DriverManager.getConnection(dbConnection);
		   Statement statement = connection.createStatement();
		   statement.executeUpdate("DELETE FROM " + tableName + " WHERE id='"+id+"'");
		}
		catch(SQLException e){  System.err.println(e.getMessage()); return false; }
		finally {         
	        try{
	        	if(connection != null)
	        		connection.close();
	        }
	        catch(SQLException e){ System.err.println(e); return false; }
        }
		return true;
	}
	
	//LECTURE PLUSIEURS PERSONNE
	//Renvoie la liste des personnes stockées dans la base de données
	public static ArrayList<Personne> getAllPersonne() {
		ArrayList<Personne> listePersonne = new ArrayList<Personne>();
		try { Class.forName("org.sqlite.JDBC"); } catch (ClassNotFoundException e1) {e1.printStackTrace(); }
		Connection connection = null;
		try{
		   connection = DriverManager.getConnection(dbConnection);
		   Statement statement = connection.createStatement();
		   
		   ResultSet resultSet = statement.executeQuery("SELECT * FROM personne");
		   while(resultSet.next()) {
			   Personne personne = new Personne(resultSet.getString("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("job"));
			   listePersonne.add(personne);
		   }
		   
		}
		catch(SQLException e){  System.err.println(e.getMessage()); }
		finally {         
	        try{
	        	if(connection != null)
	        		connection.close();
	        }
	        catch(SQLException e){ System.err.println(e); }
        }
		return listePersonne;
	}
	
	//LECTURE UNE PERSONNE
	public static Personne getPersonneFromId(String id) {
		Personne res = null;
		for(Personne personne : getAllPersonne()) {
			if(personne.getId().equals(id)) {
				res = personne;
				break;
			}
		}
		return res;
	}
	
	
	//LECTURE PLUSIEURS SALLES
	//Renvoie la liste des salles stockées dans la base de données
	public static ArrayList<Salle> getAllSalle() {
		ArrayList<Salle> listeSalle = new ArrayList<Salle>();
		try { Class.forName("org.sqlite.JDBC"); } catch (ClassNotFoundException e1) { e1.printStackTrace(); }
		Connection connection = null;
		try{
		   connection = DriverManager.getConnection(dbConnection);
		   Statement statement = connection.createStatement();
		   
		   ResultSet resultSet = statement.executeQuery("SELECT * FROM salle");
		   while(resultSet.next()) {
			   Salle salle = new Salle(resultSet.getString("id"), resultSet.getString("nom"));
			   listeSalle.add(salle);
		   }
		   
		}
		catch(SQLException e){  System.err.println(e.getMessage()); }
		finally {         
	        try{
	        	if(connection != null)
	        		connection.close();
	        }
	        catch(SQLException e){ System.err.println(e); }
        }
		return listeSalle;
	}
	
	//LECTURE UNE SALLE
	public static Salle getSalleFromId(String id) {
		Salle res = null;
		for(Salle salle : getAllSalle()) {
			if(salle.getId().equals(id)) {
				res = salle;
				break;
			}
		}
		return res;
	}
	
	//LECTURE PLUSIEURS CRENEAUX
	//Renvoie la liste des créneaux stockées dans la base de données
	public static ArrayList<Creneau> getAllCreneau() {
		ArrayList<Creneau> listeCreneau = new ArrayList<Creneau>();
		try { Class.forName("org.sqlite.JDBC"); } catch (ClassNotFoundException e1) { e1.printStackTrace(); }
		Connection connection = null;
		try{
		   connection = DriverManager.getConnection(dbConnection);
		   Statement statement = connection.createStatement();
		   ResultSet resultSet = statement.executeQuery("SELECT * FROM creneau");
		   while(resultSet.next()) {
			   Date start = null; Date end = null;
			   try{
				   start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("start"));
				   end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("end"));
			   } catch(ParseException e) { e.printStackTrace(); }
			   Creneau creneau = new Creneau(resultSet.getString("id"), start, end);
			   listeCreneau.add(creneau);
		   }
		   
		}
		catch(SQLException e){  System.err.println(e.getMessage()); }
		finally {         
	        try{
	        	if(connection != null)
	        		connection.close();
	        }
	        catch(SQLException e){ System.err.println(e); }
        }
		return listeCreneau;
	}
	
	//LECTURE UN CRENEAU
	public static Creneau getCreneauFromId(String id) {
		Creneau res = null;
		for(Creneau creneau : getAllCreneau()) {
			if(creneau.getId().equals(id)) {
				res = creneau;
				break;
			}
		}
		return res;
	}
	
	
	//LECTURE PLUSIEURS RESERVATIONS
	//Renvoie la liste des personnes stockées dans la base de données
	public static ArrayList<Reservation> getAllReservation() {
		ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();
		try { Class.forName("org.sqlite.JDBC"); } catch (ClassNotFoundException e1) {e1.printStackTrace(); }
		Connection connection = null;
		try{
		   connection = DriverManager.getConnection(dbConnection);
		   Statement statement = connection.createStatement();
		   
		   ResultSet resultSet = statement.executeQuery("SELECT * FROM reservation");
		   while(resultSet.next()) {
			   Personne personne = getPersonneFromId(resultSet.getString("idPersonne"));
			   Salle salle = getSalleFromId(resultSet.getString("idSalle"));
			   Creneau creneau = getCreneauFromId(resultSet.getString("idCreneau"));
			   Reservation reservation = new Reservation(resultSet.getString("id"), personne, salle, creneau);
			   listeReservation.add(reservation);
		   }
		   
		}
		catch(SQLException e){  System.err.println(e.getMessage()); }
		finally {         
	        try{
	        	if(connection != null)
	        		connection.close();
	        }
	        catch(SQLException e){ System.err.println(e); }
        }
		return listeReservation;
	}
	
	//MODIFICATION UNE RESERVATION
	// Renvoie true si le creneau de la reservation a bien été modifié
	public static boolean updateCreneauReservation(String idReservation, String idCreneau) {
		try { Class.forName("org.sqlite.JDBC"); } catch (ClassNotFoundException e1) {e1.printStackTrace(); }
		Connection connection = null;
		try{
		   connection = DriverManager.getConnection(dbConnection);
		   Statement statement = connection.createStatement();
		   statement.executeUpdate("UPDATE reservation SET idCreneau='"+idCreneau+"' WHERE id='"+idReservation+"'");
		}
		catch(SQLException e){  System.err.println(e.getMessage()); return false; }
		finally {         
	        try{
	        	if(connection != null)
	        		connection.close();
	        }
	        catch(SQLException e){ System.err.println(e); return false; }
        }
		return true;
	}

}
