package taxis.metier;

import java.sql.Date;

public class VueAdresses {

    protected int idLoc;
    protected Date dateLoc;
    protected int kmtotal;
    protected float accompte;
    protected float total;
    protected int idTaxi;
    protected int idClient;
    protected int cpDepart;
    protected String localiteDepart;
    protected String rueDepart;
    protected String numDepart;
    protected int cpArrivee;
    protected String localiteArrivee;
    protected String rueArrivee;
    protected String numArrivee;

    public VueAdresses() {

    }

    /**
     * constructeur paramétré
     *
     * @param idLoc identifiant unique de la location, affecté par la base de
     * données
     * @param dateLoc date de la location
     * @param kmtotal total de km que le taxi effectue
     * @param accompte accompte de la location
     * @param total prix total de la location
     * @param idTaxi identifiant unique du taxi loué
     * @param idClient identifiant unique du client qui loue le taxi
     * @param cpDepart code postal du lieu de dépard
     * @param localiteDepart nom de la ville du lieu de dépard
     * @param rueDepart nom de la rue du lieu de dépard
     * @param numDepart emplacement dans la rue du lieu de dépard
     * @param cpArrivee code postal du lieu d'arrivée
     * @param localiteArrivee nom de la ville du lieu d'arrivée
     * @param rueArrivee nom de la rue du lieu d'arrivée
     * @param numArrivee emplacement dans la rue du lieu d'arrivée
     */
    public VueAdresses(int idLoc, Date dateLoc, int kmtotal, float accompte, float total, int idTaxi, int idClient, int cpDepart, String localiteDepart,
            String rueDepart, String numDepart, int cpArrivee, String localiteArrivee, String rueArrivee, String numArrivee) {
        this.idLoc = idLoc;
        this.dateLoc = dateLoc;
        this.kmtotal = kmtotal;
        this.accompte = accompte;
        this.total = total;
        this.idTaxi = idTaxi;
        this.idClient = idClient;
        this.cpDepart = cpDepart;
        this.localiteDepart = localiteDepart;
        this.rueDepart = rueDepart;
        this.numDepart = numDepart;
        this.cpArrivee = cpArrivee;
        this.localiteArrivee = localiteArrivee;
        this.rueArrivee = rueArrivee;
        this.numArrivee = numArrivee;
    }

    public int getIdLoc() {
        return idLoc;
    }

    public void setIdLoc(int idLoc) {
        this.idLoc = idLoc;
    }

    public Date getDateLoc() {
        return dateLoc;
    }

    public void setDateLoc(Date dateLoc) {
        this.dateLoc = dateLoc;
    }

    public int getKmtotal() {
        return kmtotal;
    }

    public void setKmtotal(int kmtotal) {
        this.kmtotal = kmtotal;
    }

    public float getAccompte() {
        return accompte;
    }

    public void setAccompte(float accompte) {
        this.accompte = accompte;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getIdTaxi() {
        return idTaxi;
    }

    public void setIdTaxi(int idTaxi) {
        this.idTaxi = idTaxi;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getCpDepart() {
        return cpDepart;
    }

    public void setCpDepart(int cpDepart) {
        this.cpDepart = cpDepart;
    }

    public String getLocaliteDepart() {
        return localiteDepart;
    }

    public void setLocaliteDepart(String localiteDepart) {
        this.localiteDepart = localiteDepart;
    }

    public String getRueDepart() {
        return rueDepart;
    }

    public void setRueDepart(String rueDepart) {
        this.rueDepart = rueDepart;
    }

    public String getNumDepart() {
        return numDepart;
    }

    public void setNumDepart(String numDepart) {
        this.numDepart = numDepart;
    }

    public int getCpArrivee() {
        return cpArrivee;
    }

    public void setCpArrivee(int cpArrivee) {
        this.cpArrivee = cpArrivee;
    }

    public String getLocaliteArrivee() {
        return localiteArrivee;
    }

    public void setLocaliteArrivee(String localiteArrivee) {
        this.localiteArrivee = localiteArrivee;
    }

    public String getRueArrivee() {
        return rueArrivee;
    }

    public void setRueArrivee(String rueArrivee) {
        this.rueArrivee = rueArrivee;
    }

    public String getNumArrivee() {
        return numArrivee;
    }

    public void setNumArrivee(String numArrivee) {
        this.numArrivee = numArrivee;
    }

    @Override
    public String toString() {
        return "Location{" + "idLoc=" + idLoc + ", dateLoc=" + dateLoc + ", kmtotal=" + kmtotal + ", accompte=" + accompte + ", total=" + total + ", idTaxi=" + idTaxi + ", idClient=" + idClient + ", cpDepart=" + cpDepart + ", localiteDepart=" + localiteDepart + ", rueDepart=" + rueDepart + ", numDepart=" + numDepart + ", cpArrivee=" + cpArrivee + ", localiteArrivee=" + localiteArrivee + ", rueArrivee=" + rueArrivee + ", numArrivee=" + numArrivee + '}';
    }

}
