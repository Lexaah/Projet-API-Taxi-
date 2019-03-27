package taxis.metier;

public class Client {

    protected int idClient;
    protected String nom;
    protected String prenom;
    protected String tel;
    protected int idAdr;

    public Client(int idClient, String nom, String prenom, String tel, int idAdr) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.idAdr = idAdr;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getIdAdr() {
        return idAdr;
    }

    public void setIdAdr(int idAdr) {
        this.idAdr = idAdr;
    }

    @Override
    public String toString() {
        return "Client: " + nom + " " + prenom + "\nn° de téléphone [+" + tel + "]";
    }
}
