import java.util.Date;
import java.sql.*;

public class Ressources {
	
	public static void show_Tables(Connection connection)
    {
		//for more informations !!!!
		//https://stackoverflow.com/questions/34774331/how-to-get-table-information-in-a-database-sqlite
		
		
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
	    	// if the error message is "out of memory", 
	    	// it probably means no database file is found
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
	    
        return ;
    }

	public static void main(String[] args) throws ClassNotFoundException
	{
		Salle sa = new Salle();
		Personne per = new Personne();
		Creneau cre = new Creneau();
		Reservation resa = new Reservation();
		
		
		// load the sqlite-JDBC driver using the current class loader
	    Class.forName("org.sqlite.JDBC");

	    Connection connection = null;
	    try
	    {
	    	// create a database connection
	    	//connection = DriverManager.getConnection("jdbc:sqlite:");
	    	connection = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
	    	
	    	Statement statement = connection.createStatement();
	    	
	    	statement.setQueryTimeout(30);  // set timeout to 30 sec.

	    	statement.executeUpdate("drop table if exists Salle");
	    	statement.executeUpdate("drop table if exists Personne");
	    	//statement.executeUpdate("drop table if exists Creneau");
	    	
	    	statement.executeUpdate("create table Salle (IDSalle integer, Name string, constraint IDSPK PRIMARY KEY (IDSalle))");
	    	statement.executeUpdate("create table Personne (IDPersonne integer, name string, constraint IDPPK PRIMARY KEY (IDPersonne))");
	    	//statement.executeUpdate("create table Creneau (start date, end date)");
	    	
	    	show_Tables(connection);
	    	
	    	/*statement.executeUpdate("insert into person values(1, 'leo')");
	    	ResultSet rs = statement.executeQuery("SELECT * FROM sqlite_master");
	    	while(rs.next())
	    	{
	    		// read the result set
	    		System.out.println("name = " + rs.getString("name"));
	    		System.out.println("id = " + rs.getInt("id"));
	    		System.out.println(rs);
	    	}*/
	    	
	    }
	    catch(SQLException e)
	    {
	    	// if the error message is "out of memory", 
	    	// it probably means no database file is found
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
	    
	    System.out.println("pas de bug");
		
		return;
	}
}