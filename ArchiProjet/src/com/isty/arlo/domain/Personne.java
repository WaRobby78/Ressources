package com.isty.arlo.domain;

public class Personne
{
	private Integer id;
    private String nom;
    private String prenom;
    private String job;
    
    public Personne()
    {
    	this.id = 0;
        this.nom = "unknown";
        this.prenom = "unknown";
        this.job = "unknown";
    }

    public Personne(String nom, String prenom, String job)
    {
    	this.id = null;
        this.nom = nom;
        this.prenom = prenom;
        this.job = job;
    }
    
    public Integer getId() {
		return id;
	}
    
    public void setId(Integer id) {
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