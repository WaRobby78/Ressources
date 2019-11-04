package com.isty.arlo.domain;

public class Personne
{
	private String id;
    private String nom;
    private String prenom;
    private String job;
    

    public Personne(String id, String nom, String prenom, String job)
    {
    	this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.job = job;
    }
    
    public String getId() {
		return id;
	}
    
    public void setId(String id) {
    	this.id = id;
    }

	public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getJob()
    {
        return job;
    }

    public void setJob(String job)
    {
        this.job = job;
    }
}