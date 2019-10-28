public class Salle
{	
	private int id;
    private String name;
    //private String place;
    
    public Salle()
    {
    	this.id = 0;
        this.name = "new";
        //this.place = "unknown";
    }

    //public Salle(int id, String name, String place)
    public Salle(int id, String name)
    {
    	this.id = id;
        this.name = name;
        //this.place = place;
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /*public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }*/
}