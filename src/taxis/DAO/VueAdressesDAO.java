package taxis.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import taxis.metier.VueAdresses;

public class VueAdressesDAO extends DAO<VueAdresses> {
    
    /**
     * lecture de la vue "vue_location" sur base d'un id entré 
     *
     * @throws SQLException recherche impossible
     * @param idrech : id de la location à rechercher
     * @return données de la vue vue_location
     */

    public List<VueAdresses> rechLoc(int idrech) throws SQLException {

        String req = "SELECT * FROM vue_adresses WHERE idloc = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            List<VueAdresses> loc = new ArrayList<>();

            pstm.setInt(1, idrech);
            try (ResultSet rs = pstm.executeQuery()) {
                boolean trouve = false;
                while (rs.next()) {
                    trouve = true;
                    int idloc = rs.getInt("IDLOC");
                    String dateloc = rs.getString("DATELOC");
                    int kmtotal = rs.getInt("KMTOTAL");
                    float acompte = rs.getFloat("ACOMPTE");
                    float total = rs.getFloat("TOTAL");
                    int idclient = rs.getInt("IDCLIENT");
                    int idtaxi = rs.getInt("IDTAXI");
                    int cp = rs.getInt("CP");
                    String localite = rs.getString("LOCALITE");
                    String rue = rs.getString("RUE");
                    String num = rs.getString("NUM");
                    int cpRetour = rs.getInt("CP arrivée");
                    String localiteRetour = rs.getString("LOC arrivée");
                    String rueRetour = rs.getString("RUE arrivée");
                    String numRetour = rs.getString("NUM arrivée");

                    loc.add(new VueAdresses(idloc, dateloc, kmtotal, acompte, total, idclient, idtaxi, cp, localite, rue, num, cpRetour, localiteRetour, rueRetour, numRetour));
                    }
                    if (!trouve) {
                        throw new SQLException("Recherche impossible");
                    } else {
                        return loc;
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
