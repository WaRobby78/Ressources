public class Personne
{	
	private int id;
    private String name;
    //private String job;
    
    public Personne()
    {
    	this.id = 0;
        this.name = "new";
        //this.job = "unknown";
    }

    //public Personne(int id, String name, String job)
    public Personne(int id, String name)
    {
    	this.id = id;
        this.name = name;
        //this.job = job;
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

    /*public String getJob()
    {
        return job;
    }

    public void setJob(String job)
    {
        this.job = job;
    }*/
}