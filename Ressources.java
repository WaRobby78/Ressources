import java.sql.*;
import java.util.*;
import java.io.*;

public class Ressources {
	
	public static void initialisation_bd(Connection connection)
    {
		try
		{
			Statement statement = connection.createStatement();
			statement.executeUpdate("drop table if exists Salle");
	    	statement.executeUpdate("drop table if exists Personne");
	    	//statement.executeUpdate("drop table if exists Creneau");
	    	
	    	//statement.executeUpdate("create table Salle (IDSalle integer, Name string, constraint IDSPK PRIMARY KEY (IDSalle))");
	    	statement.executeUpdate("create table Salle (IDSalle INTEGER PRIMARY KEY AUTOINCREMENT, Name string)");
	    	statement.executeUpdate("create table Personne (IDPersonne INTEGER PRIMARY KEY AUTOINCREMENT, Name string)");
	    	//statement.executeUpdate("create table Creneau (start date, end date)");
	    	
		}
	    catch(SQLException e)
	    {
	    	// if the error message is "out of memory", it probably means no database file is found
	    	System.err.println(e.getMessage());
	    }
	    
        return ;
    }
	
	public static void show_Tables(Connection connection)
    {
		try
		{
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
		    while (rs.next())
		    {
		        System.out.println(rs.getString("TABLE_NAME"));
		    }
		}
	    catch(SQLException e)
	    {
	    	// if the error message is "out of memory", it probably means no database file is found
	    	System.err.println(e.getMessage());
	    }
		
        return ;
    }
	
	public static void add_Salle(Connection connection, String name)
    {
		try
		{
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into Salle (Name) values('"+name+"')");
		}
	    catch(SQLException e)
	    {
	    	// if the error message is "out of memory", it probably means no database file is found
	    	System.err.println(e.getMessage());
	    }
	    
        return ;
    }
	
	public static void show_Salle(Connection connection)
    {
		try
		{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Salle");
	    	while(rs.next())
	    	{
	    		// read the result set
	    		System.out.println("id = " + rs.getInt("IDSalle"));
	    		System.out.println("name = " + rs.getString("Name"));
	    	}
		}
	    catch(SQLException e)
	    {
	    	// if the error message is "out of memory", it probably means no database file is found
	    	System.err.println(e.getMessage());
	    }
		
        return ;
    }
	
	public static void main(String[] args) throws ClassNotFoundException
	{
		Scanner scan = new Scanner(System.in);
		String test;
		
		/*Salle sa = new Salle();
		Personne per = new Personne();
		Creneau cre = new Creneau();
		Reservation resa = new Reservation();*/
		
		// load the sqlite-JDBC driver using the current class loader
	    Class.forName("org.sqlite.JDBC");

	    Connection connection = null;
	    try
	    {
	    	// create a database connection
	    	connection = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
	    	Statement statement = connection.createStatement();
	    	
	    	statement.setQueryTimeout(30);  // set timeout to 30 sec.
	    	
	    	do
			{
				System.out.println("");
				System.out.println(" To show all the Tables from your database, enter all.");
				System.out.println(" To add something in one of your databse enter, add.");
				System.out.println(" To the containts of one of your Tables, enter show.");
				System.out.println(" To reset everything, enter init.");
				System.out.println(" To exit the programm, enter exit.");
				System.out.println();
				test = scan.next();
				
				if(test.toLowerCase().equals("all"))
				{
					show_Tables(connection);
				}
				
				if(test.toLowerCase().equals("show"))
				{
					show_Salle(connection);
				}
				
				if(test.toLowerCase().equals("init"))
				{
					initialisation_bd(connection);
				}
				
				if(test.toLowerCase().equals("add"))
				{
					add_Salle(connection,"bjr");
				}
			
			}while(!test.toLowerCase().equals("exit"));
	    }
	    catch(SQLException e)
	    {
	    	// if the error message is "out of memory", it probably means no database file is found
	    	System.err.println(e.getMessage());
	    }
	    finally
	    {
	    	try
	    	{
	    		if(connection != null)
	    			connection.close();
	    	}
	    	catch(SQLException e)
	    	{
	    		// connection close failed.
	    		System.err.println(e);
	    	}
	    }
	    
	    System.out.println("Goodbye !");
		scan.close();
		
		return;
	}
}