package projettaxi;

import java.sql.*;
import java.text.ParseException;
import myconnections.DBConnection;
import java.util.Scanner;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import taxis.DAO.TaxisDAO;
import taxis.DAO.DAO;
import taxis.DAO.LocationDAO;
import taxis.DAO.VueAdressesDAO;
import taxis.metier.Location;
import taxis.metier.Taxis;
import taxis.metier.VueAdresses;

public class ProjetTaxi {

    Scanner sc1 = new Scanner(System.in);
    Taxis taxiActuel = null;
    DAO<Taxis> taxisDAO = null;
    Location locationActuel = null;
    DAO<Location> locationDAO = null;
    DAO<VueAdresses> vueAdressesDAO = null;

    public void menu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("Connexion établie.");

        taxisDAO = new TaxisDAO();
        taxisDAO.setConnection(dbConnect);
        vueAdressesDAO = new VueAdressesDAO();
        vueAdressesDAO.setConnection(dbConnect);
        locationDAO = new LocationDAO(); //Exception in thread "main" java.lang.ExceptionInInitializerError
        locationDAO.setConnection(dbConnect);

        int choix;
        do {
            System.out.println("\t--GESTION DE TAXI--");
            System.out.println("1. Créer un nouveau profil (taxi)");
            System.out.println("2. Recherche exacte par l'ID");
            System.out.println("3. Recherche partielle d'une description");
            System.out.println("4. Modifier les données d'un profil (taxi)");
            System.out.println("5. Supprimer un profil (taxi)");
            System.out.println("6. Créer une location");
            System.out.println("7. Rechercher une location");
            System.out.println("8. Modifier une location");
            System.out.println("9. Supprimer une location");
            System.out.println("10. Fin de programme");
            System.out.println("\nChoix: ");
            choix = sc.nextInt();

            switch (choix) {
                case 1:
                    creationTaxi();
                    break;
                case 2:
                    rechercheID();
                    break;
                case 3:
                    rechercheDescription();
                    break;
                case 4:
                    modifierTaxi();
                    break;
                case 5:
                    effacerTaxi();
                    break;
                case 6: {
                    try {
                        creationLocation();
                    } catch (ParseException ex) {
                        Logger.getLogger(ProjetTaxi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case 7:
                    rechercheLocation();
                    break;
                case 8: {
                    try {
                        modifierLocation();
                    } catch (ParseException ex) {
                        Logger.getLogger(ProjetTaxi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case 9:
                    effacerLocation();
                    break;
                case 10:
                    System.out.println("\n--- FIN ---");
                    break;
                default:
                    System.out.println("Choisissez un chiffre proposé.");
                    break;
            }
            System.out.println("\n");
        } while (choix != 10);

    }

    public void creationTaxi() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\t-Nouveau taxi-");
        System.out.print("Immatriculation :");
        String immatriculation = sc.nextLine();
        System.out.print("Carburant :");
        String carburant = sc.nextLine();
        System.out.print("Prix au kilomètre :");
        float prixKm = sc.nextFloat();
        sc.skip("\n");
        System.out.println("Brève description: ");
        String description = sc.nextLine();

        taxiActuel = new Taxis(0, immatriculation, carburant, prixKm, description);
        try {
            taxiActuel = taxisDAO.create(taxiActuel);
            System.out.println("\nNOUVEAU TAXI: " + taxiActuel);
        } catch (SQLException e) {
            System.out.println("Erreur d'ajout:" + e);
        }
    }

    public void rechercheID() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("\n\t-Recherche par l'ID-");
            System.out.print("ID du taxi recherché: ");
            int rechID = sc.nextInt();
            taxiActuel = taxisDAO.read(rechID);
            System.out.println(taxiActuel);

        } catch (SQLException e) {
            System.out.println("ERREUR SQL:" + e.getMessage());
        }
    }

    public void rechercheDescription() {

        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t-Recherche par la description-");
        System.out.print("Mot clé: ");
        String descript = sc.nextLine();
        try {
            List<Taxis> abc = ((TaxisDAO) taxisDAO).rechDescription(descript);
            for (Taxis tx : abc) {
                System.out.println(tx);
            }
        } catch (SQLException e) {
            System.out.println("ERREUR SQL:" + e.getMessage());
        }

    }

    public void modifierTaxi() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\t-Modification d'un profil (taxi)-");
        System.out.println("Entrez l'ID du taxi à modifier: ");
        int ID = sc.nextInt();
        sc.skip("\n");
        System.out.println("Nouvelle immatriculation: ");
        String newImmatriculation = sc.nextLine();
        System.out.println("Carburant :");
        String newCarburant = sc.nextLine();
        System.out.println("Nouveau prix au kilomètre: ");
        float newPrixKm = sc.nextFloat();
        sc.skip("\n");
        System.out.println("Nouvelle description: ");
        String newDescription = sc.nextLine();

        taxiActuel = new Taxis(ID, newImmatriculation, newCarburant, newPrixKm, newDescription);
        try {
            taxisDAO.update(taxiActuel);
        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }

    }

    public void effacerTaxi() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\n\t-Suppression d'un profil-");
        System.out.print("\nID du taxi: ");
        int idTaxi = sc.nextInt();

        taxiActuel = new Taxis(idTaxi, "", "", 0, "");
        try {
            taxisDAO.delete(taxiActuel);
        } catch (SQLException e) {
            System.out.println("ERREUR SQL: " + e.getMessage());
        }

    }

    public void creationLocation() throws ParseException {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\t-Nouvelle location-");
        System.out.print("Date de la location: ");
        String dateLoc = sc.nextLine();
        System.out.print("Km total: ");
        int kmTotal = sc.nextInt();
        System.out.print("Acompte: ");
        float acompte = sc.nextFloat();
        sc.skip("\n");
        System.out.println("Prix total: ");
        float total = sc.nextFloat();
        sc.skip("\n");
        System.out.println("ID de l'adresse de dépard: ");
        int idAdrDebut = sc.nextInt();
        System.out.println("ID de l'adresse d'arrivée: ");
        int idAdrFin = sc.nextInt();
        System.out.println("ID du taxi: ");
        int idTaxi = sc.nextInt();
        System.out.println("ID du client: ");
        int id = sc.nextInt();
        locationActuel = new Location(0, dateLoc, kmTotal, acompte, total, idAdrDebut, idAdrFin, idTaxi, id);
        try {
            locationActuel = locationDAO.create(locationActuel);
            System.out.println("Location : " + locationActuel);
        } catch (SQLException e) {
            System.out.println("ERREUR SQL: " + e.getMessage());
        }

    }

    public void rechercheLocation() throws SQLException {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("\n\t-Recherche d'une location-");
            System.out.print("ID de la location recherché: ");
            int IDLoc = sc.nextInt();
            List<VueAdresses> loc = ((VueAdressesDAO) vueAdressesDAO).rechLoc(IDLoc);
            System.out.println(loc);

        } catch (SQLException e) {
            System.out.println("ERREUR SQL: " + e.getMessage());
        }
    }

    public void modifierLocation() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t-Modification d'une location-");
        System.out.println("ID de la location recherchée: ");
        int idLoc = sc.nextInt();
        sc.skip("\n");
        System.out.println("Nouvelle date: ");
        String dateLoc = sc.nextLine();
        System.out.println("Nouveau km total: ");
        int kmTotal = sc.nextInt();
        System.out.println("Nouvel acompte:");
        float acompte = sc.nextFloat();
        sc.skip("\n");
        System.out.println("Nouveau prix total: ");
        float total = sc.nextFloat();
        sc.skip("\n");
        System.out.println("Nouvelle id de l'adresse de dépard: ");
        int idAdrDebut = sc.nextInt();
        System.out.println("Nouvelle  id de l'adresse d'arrivée: ");
        int idAdrFin = sc.nextInt();
        System.out.println("ID du nouveau taxi: ");
        int idTaxi = sc.nextInt();
        System.out.println("Nouvelle ID du client: ");
        int id = sc.nextInt();

        locationActuel = new Location(0, dateLoc, kmTotal, acompte, total, idAdrDebut, idAdrFin, idTaxi, id);
        try {
            locationActuel = locationDAO.update(locationActuel);
            System.out.println("Location : " + locationActuel);
        } catch (SQLException e) {
            System.out.println("ERREUR SQL: " + e.getMessage());
        }

    }

    public void effacerLocation() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\n\t-Suppression d'une location-");
        System.out.print("\nID de la location: ");
        int idLoc = sc.nextInt();

        locationActuel = new Location(idLoc, null, 0, 0, 0, 0, 0, 0, 0);
        try {
            locationDAO.delete(locationActuel);
        } catch (SQLException e) {
            System.out.println("ERREUR SQL: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        ProjetTaxi pt = new ProjetTaxi();
        pt.menu();
    }

}
