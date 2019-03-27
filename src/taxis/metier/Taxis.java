package taxis.metier;

public class Taxis {

    protected int idTaxi;
    protected String immatriculation;
    protected String carburant;
    protected float prixKm;
    protected String description;

    public Taxis() {

    }

    /**
     * constructeur paramétré
     *
     * @param idTaxi identifiant unique du taxi, affecté par la base de données
     * @param immatriculation immatriculation du taxi
     * @param carburant type de carburant du taxi
     * @param prixKm tarifs du taxi (prix au kilomètre)
     */
    public Taxis(int idTaxi, String immatriculation, String carburant, float prixKm, String description) {
        this.idTaxi = idTaxi;
        this.immatriculation = immatriculation;
        this.carburant = carburant;
        this.prixKm = prixKm;
        this.description = description;
    }

    public int getIdTaxi() {
        return idTaxi;
    }

    public void setIdTaxi(int idTaxi) {
        this.idTaxi = idTaxi;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public float getPrixKm() {
        return prixKm;
    }

    public void setPrixKm(float prixKm) {
        this.prixKm = prixKm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nTaxi n°" + idTaxi + " [" + immatriculation + "]: " + carburant + " " + prixKm + "€/km\nDescription: " + description + "\t___________";
    }
}
