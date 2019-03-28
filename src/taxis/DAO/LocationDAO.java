package taxis.DAO;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import taxis.metier.Location;
import taxis.metier.Taxis;
import taxis.metier.VueAdresses;

public class LocationDAO extends DAO<VueAdresses> {

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
                    Date dateloc = rs.getDate("DATELOC");
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
    public VueAdresses read(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VueAdresses create(VueAdresses obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VueAdresses update(VueAdresses obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(VueAdresses obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
