public class Reservation
{	
	private Personne personne;
    private Salle salle;
    private Creneau creneau;
    
    public Reservation()
    {
        this.salle = null;
        this.creneau = null;
        this.personne = null;
    }

    public Reservation(Salle salle, Creneau creneau, Personne personne)
    {
        this.salle = salle;
        this.creneau = creneau;
        this.personne = personne;
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