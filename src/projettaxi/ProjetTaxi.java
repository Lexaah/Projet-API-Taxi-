package projettaxi;

import java.sql.*;
import myconnections.DBConnection;
import java.util.Scanner;
import java.util.List;
import taxis.DAO.TaxisDAO;
import taxis.DAO.DAO;
import taxis.metier.Taxis;

public class ProjetTaxi {

    Scanner sc1 = new Scanner(System.in);
    Taxis taxiActuel = null;
    DAO<Taxis> taxisDAO = null;

    public void menu() {
        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("Connexion établie.");

        taxisDAO = new TaxisDAO();
        taxisDAO.setConnection(dbConnect);

        int choix;
        do {
            System.out.println("\t--GESTION DE TAXI--");
            System.out.println("1. Créer un nouveau profil (taxi)");
            System.out.println("2. Recherche exacte par l'ID");
            System.out.println("3. Recherche partielle d'une description");
            System.out.println("4. Modifier les données d'un profil (taxi)");
            System.out.println("5. Supprimer un profil (taxi)");
            System.out.println("6. Fin de programme");
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
                case 6:
                    System.out.println("\n--- FIN ---");
                    break;
                default:
                    System.out.println("Choisissez un chiffre proposé.");
                    break;
            }
            System.out.println("\n");
        } while (choix != 6);

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
            System.out.println("ERREUR SQL: " + e.getMessage());
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

    public static void main(String[] args) {
        ProjetTaxi pt = new ProjetTaxi();
        pt.menu();
    }

}
