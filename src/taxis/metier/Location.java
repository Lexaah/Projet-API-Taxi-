package taxis.metier;

import java.time.LocalDate;
import java.sql.*;
import java.util.*;
import myconnections.DBConnection;

public class Location {

    protected int idLoc;
    protected LocalDate dateLoc;
    protected int kmtotal;
    protected float accompte;
    protected float total;
    protected int idAdrDebut;
    protected int idAdrFin;
    protected int idTaxi;
    protected int idClient;

    public Location() {

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
     * @param idAdrDebut identifiant unique de l'adresse de dépard
     * @param idAdrFin identifiant unique de l'adresse de l'arrivée
     * @param idTaxi identifiant unique du taxi loué
     * @param idClient identifiant unique du client qui loue le taxi
     */
    public Location(int idLoc, LocalDate dateLoc, int kmtotal, float accompte, float total, int idAdrDebut, int idAdrFin, int idTaxi, int idClient) {
        this.idLoc = idLoc;
        this.dateLoc = dateLoc;
        this.kmtotal = kmtotal;
        this.accompte = accompte;
        this.total = total;
        this.idAdrDebut = idAdrDebut;
        this.idAdrFin = idAdrFin;
        this.idTaxi = idTaxi;
        this.idClient = idClient;
    }

    public int getIdLoc() {
        return idLoc;
    }

    public void setIdLoc(int idLoc) {
        this.idLoc = idLoc;
    }

    public LocalDate getDateLoc() {
        return dateLoc;
    }

    public void setDateLoc(LocalDate dateLoc) {
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

    public int getIdAdrDebut() {
        return idAdrDebut;
    }

    public void setIdAdrDebut(int idAdrDebut) {
        this.idAdrDebut = idAdrDebut;
    }

    public int getIdAdrFin() {
        return idAdrFin;
    }

    public void setIdAdrFin(int idAdrFin) {
        this.idAdrFin = idAdrFin;
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

}
