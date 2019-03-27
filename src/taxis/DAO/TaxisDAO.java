package taxis.DAO;

import java.sql.*;
import java.util.*;
import taxis.metier.Taxis;

public class TaxisDAO extends DAO<Taxis> {

    /**
     * création d'un taxi
     *
     * @throws SQLException erreur de création
     * @param obj taxi à créer
     * @return taxi créé
     */
    @Override
    public Taxis create(Taxis obj) throws SQLException {

        String req1 = "INSERT INTO taxi(immatriculation,carburant,prixKm,description) VALUES(?,?,?,?)";
        String req2 = "SELECT idTaxi FROM taxi WHERE immatriculation=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req1);
                PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm1.setString(1, obj.getImmatriculation());
            pstm1.setString(2, obj.getCarburant());
            pstm1.setFloat(3, obj.getPrixKm());
            pstm1.setString(4, obj.getDescription());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                throw new SQLException("Erreur.. Taxi non créé.");
            }
            pstm2.setString(1, obj.getImmatriculation());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idtaxi = rs.getInt(1);
                    obj.setIdTaxi(idtaxi);
                    return read(idtaxi);
                } else {
                    throw new SQLException("Erreur: record introuvable.");
                }
            }
        }
    }

    /**
     * récupération des données d'un taxi sur base de son ID
     *
     * @throws SQLException code inconnu
     * @param idTaxi identifiant du taxi
     * @return taxi trouvé
     */
    @Override
    public Taxis read(int idTaxi) throws SQLException {

        String req = "SELECT * FROM taxi WHERE idtaxi = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, idTaxi);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    String immatr = rs.getString("IMMATRICULATION");
                    String carburant = rs.getString("CARBURANT");
                    float prixKm = rs.getFloat("PRIXKM");
                    String description = rs.getString("DESCRIPTION");
                    return new Taxis(idTaxi, immatr, carburant, prixKm, description);

                } else {
                    throw new SQLException("Cette ID n'existe pas.");
                }

            }
        }
    }

    /**
     * méthode permettant de récupérer tous les taxi ayant un certain mot dans
     * leur description
     *
     * @param motrech mot recherché
     * @return liste de taxi
     * @throws SQLException mot inconnu
     */
    public List<Taxis> rechDescription(String motrech) throws SQLException {
        List<Taxis> taxisliste = new ArrayList<>();
        String req = "SELECT * FROM taxi WHERE description LIKE ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setString(1, "%" + motrech + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                boolean trouve = false;
                while (rs.next()) {
                    trouve = true;
                    int idTaxi = rs.getInt("IDTAXI");
                    String immatriculation = rs.getString("IMMATRICULATION");
                    String carburant = rs.getString("CARBURANT");
                    float prixKm = rs.getFloat("PRIXKM");
                    String description = rs.getString("DESCRIPTION");
                    taxisliste.add(new Taxis(idTaxi, immatriculation, carburant, prixKm, description));
                }

                if (!trouve) {
                    throw new SQLException("Aucune description ne contient ce mot.");
                } else {
                    return taxisliste;
                }
            }
        }

    }

    /**
     * mise à jour des données d'un taxi sur base de son immatriculation
     *
     * @return Taxis
     * @param obj taxi à mettre à jour
     * @throws SQLException erreur de mise à jour
     */
    @Override
    public Taxis update(Taxis obj) throws SQLException {
        String req = "UPDATE taxi SET immatriculation=?, carburant=?, prixKm=?,description=? WHERE idtaxi= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(5, obj.getIdTaxi());
            pstm.setString(1, obj.getImmatriculation());
            pstm.setString(2, obj.getCarburant());
            pstm.setFloat(3, obj.getPrixKm());
            pstm.setString(4, obj.getDescription());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("Erreur: aucun taxi ne correspond à cette ID.");
            } else {
                System.out.println("Taxi n°" + obj.getIdTaxi() + " modifié.");
            }
            return read(obj.getIdTaxi());
        }
    }

    /**
     * effacement du taxi sur base de son immatriculation
     *
     * @throws SQLException erreur d'effacement
     * @param obj taxi à effacer
     */
    @Override
    public void delete(Taxis obj) throws SQLException {

        String req = "DELETE FROM TAXI WHERE idtaxi= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, obj.getIdTaxi());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("Erreur: aucun taxi supprimé.");
            } else {
                System.out.println("Taxi n°" + obj.getIdTaxi() + " supprimé.");
            }
        }
    }

}
