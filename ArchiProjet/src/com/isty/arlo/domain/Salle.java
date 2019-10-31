package com.isty.arlo.domain;

public class Salle
{	
	private Integer id;
    private String nom;
    
    public Salle()
    {
        this.nom = "new";
    }

    public Salle(String nom)
    {
        this.nom = nom;
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
}
