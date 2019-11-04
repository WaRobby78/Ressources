package com.isty.arlo.domain;

public class Reservation
{
	private String id;
	private Personne personne;
    private Salle salle;
    private Creneau creneau;
    

    public Reservation(String id, Personne personne, Salle salle, Creneau creneau)
    {
    	this.id = id;
        this.salle = salle;
        this.creneau = creneau;
        this.personne = personne;
    }
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Personne getPersonne()
    {
        return personne;
    }

    public void setPersonne(Personne personne)
    {
        this.personne = personne;
    }

    public Salle getSalle()
    {
        return salle;
    }

    public void setSalle(Salle salle)
    {
        this.salle = salle;
    }

    public Creneau getCreneau()
    {
        return creneau;
    }

    public void setCreneau(Creneau creneau)
    {
        this.creneau = creneau;
    }
}
