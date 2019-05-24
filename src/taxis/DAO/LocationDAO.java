package taxis.DAO;

import java.sql.*;
import java.util.*;
import taxis.metier.Location;
import taxis.metier.VueAdresses;

public class LocationDAO extends DAO<Location> {

    public List<VueAdresses> rechLoc(int idrech) throws SQLException {
        List<VueAdresses> locationliste = new ArrayList<>();
        String req1 = "SELECT * FROM vue_adresses WHERE idloc = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req1)) {
            pstm.setInt(1, idrech);
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("Erreur: location non trouvée.");
            }
            try (ResultSet rs = pstm.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    int idloc = rs.getInt("IDLOC");
                    String dateloc = rs.getString("DATELOC");
                    int kmtotal = rs.getInt("KMTOTAL");
                    Float accompte = rs.getFloat("ACCOMPTE");
                    Float total = rs.getFloat("TOTAL");
                    int idclient = rs.getInt("IDCLIENT");
                    int idtaxi = rs.getInt("IDTAXI");
                    int cp = rs.getInt("CP");
                    String localite = rs.getString("LOCALITE");
                    String rue = rs.getString("RUE");
                    String num = rs.getString("NUM");
                    int cpArrivee = rs.getInt("CP arrivée");
                    String localiteArrivee = rs.getString("LOCALITE arrivée");
                    String rueArrivee = rs.getString("RUE arrivée");
                    String numArrivee = rs.getString("NUM arrivée");

                    locationliste.add(new VueAdresses(idloc, dateloc, kmtotal, accompte, total, idclient, idtaxi, cp, localite, rue, num, cpArrivee, localiteArrivee, rueArrivee, numArrivee));
                };
                if (!found) {
                    throw new SQLException("Cette ID n'existe pas.");
                } else {
                    return locationliste;
                }

            }
        }
    }

    @Override
    public Location read(int idLoc) throws SQLException {
        String req = "SELECT * FROM location WHERE idloc = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, idLoc);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {

                    String dateloc = rs.getString("DATELOC");
                    int kmtotal = rs.getInt("KMTOTAL");
                    float acompte = rs.getFloat("ACOMPTE");
                    float total = rs.getFloat("TOTAL");
                    int idclient = rs.getInt("IDCLIENT");
                    int idtaxi = rs.getInt("IDTAXI");
                    int idadrdebut = rs.getInt("IDADRDEBUT");
                    int idadrfin = rs.getInt("IDADRFIN");

                    return new Location(idLoc, dateloc, kmtotal, acompte, total, idclient, idtaxi, idadrdebut, idadrfin);

                } else {
                    throw new SQLException("Cette ID n'existe pas.");
                }
            }
        }
    }

    @Override
    public Location create(Location obj) throws SQLException {
        String query1 = "INSERT INTO location(dateloc,kmtotal,acompte,total,idadrdebut,idadrfin,idtaxi,idclient)" + "VALUES(?,?,?,?,?,?,?,?)";
        String query2 = "SELECT idloc FROM location WHERE idadrdebut =? AND idadrfin = ? AND idtaxi = ? AND idclient = ?;";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
                PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {

            pstm1.setString(1, obj.getDateLoc());
            pstm1.setInt(2, obj.getKmtotal());
            pstm1.setFloat(3, obj.getAccompte());
            pstm1.setFloat(4, obj.getTotal());
            pstm1.setInt(5, obj.getIdAdrDebut());
            pstm1.setInt(6, obj.getIdAdrFin());
            pstm1.setInt(7, obj.getIdTaxi());
            pstm1.setInt(8, obj.getIdClient());
            int nl = pstm1.executeUpdate();
            System.out.println(nl + " ligne(s) insérée.");
            pstm2.setInt(1, obj.getIdAdrDebut());
            pstm2.setInt(2, obj.getIdAdrFin());
            pstm2.setInt(3, obj.getIdTaxi());
            pstm2.setInt(4, obj.getIdClient());

            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idloc = rs.getInt(1);
                    obj.setIdLoc(idloc);
                    return read(idloc);
                } else {
                    throw new SQLException("Erreur: record introuvable");
                }
            }
        }
    }

    @Override
    public Location update(Location obj) throws SQLException {
        String query = "UPDATE location SET dateloc = ?, kmtotal = ?, acompte = ?, total = ?, idadrdebut = ?, idadrfin = ?, idtaxi = ?, idclient = ? WHERE idloc = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {

            pstm.setInt(9, obj.getIdLoc());
            pstm.setString(1, obj.getDateLoc());
            pstm.setInt(2, obj.getKmtotal());
            pstm.setFloat(3, obj.getAccompte());
            pstm.setFloat(4, obj.getTotal());
            pstm.setInt(5, obj.getIdAdrDebut());
            pstm.setInt(6, obj.getIdAdrFin());
            pstm.setInt(7, obj.getIdTaxi());
            pstm.setInt(8, obj.getIdClient());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("Aucune ligne mise à jour");
            } else {
                System.out.println("Location n° " + obj.getIdLoc() + " modifiée.");
            }
            return read(obj.getIdLoc());
        }
    }

    @Override
    public void delete(Location obj) throws SQLException {
        String query1 = "DELETE FROM location WHERE idloc = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setInt(1, obj.getIdLoc());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("Aucune ligne effacée.");
            } else {
                System.out.println("Location N°" + obj.getIdLoc() + " supprimée.");
            }

        }
    }
}
