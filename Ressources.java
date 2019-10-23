public class Client
{
    private String nom;
    private String contrat;

    public Client(String nom, String contrat){
        this.nom = nom;
        this.contrat = contrat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }
    
    public Client()
    {
    	this.nom = "new";
        this.contrat = "new";
    }
}
