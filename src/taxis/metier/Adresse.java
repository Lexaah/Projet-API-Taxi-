package taxis.metier;

public class Adresse {

    protected int idAdr;
    protected int cp;
    protected String localite;
    protected String rue;
    protected String num;

    public Adresse(int idAdr, int cp, String localite, String rue, String num) {
        this.idAdr = idAdr;
        this.cp = cp;
        this.localite = localite;
        this.rue = rue;
        this.num = num;
    }

    public int getIdAdr() {
        return idAdr;
    }

    public void setIdAdr(int idAdr) {
        this.idAdr = idAdr;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
    
    @Override
    public String toString() {
        
        return "\nAdresse de dépard: "+rue+" n°" + num + "\n " + cp + ", " + localite;
    }

}
