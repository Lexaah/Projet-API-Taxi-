/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxis.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import myconnections.DBConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import taxis.metier.Taxis;

/**
 *
 * @author Le_al
 */
public class TaxisDAOTest {

    static Connection dbConnect;

    public TaxisDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.out.println("Erreur de connexion");
            System.exit(1);
        }
        System.out.println("Connexion établie");
    }

    @AfterClass
    public static void tearDownClass() {
        DBConnection.closeConnection();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class TaxisDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Taxis obj = new Taxis(0, "TestImma", "TestDIESEL", 12, "Test Description");
        TaxisDAO instance = new TaxisDAO();
        instance.setConnection(dbConnect);
        Taxis expResult = obj;
        Taxis result = instance.create(obj);
        assertEquals("Immatriculations différentes.", expResult.getImmatriculation(), result.getImmatriculation());
        assertEquals("Carburants différents.", expResult.getCarburant(), result.getCarburant());
        assertEquals("Prixs différents.", expResult.getPrixKm(), result.getPrixKm(), 0);
        assertEquals("ID non générée.", expResult.getIdTaxi(), result.getIdTaxi());

        obj = new Taxis(0, "TestImma", "TestDIESEL", 12, "Test Description");
        try {
            Taxis result2 = instance.create(obj);
            fail("Création d'un doublon.");
            instance.delete(result2);
        } catch (SQLException e) {
        }
        instance.delete(result);
    }

    /**
     * Test of read method, of class TaxisDAO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        int idTaxi = 0;
        TaxisDAO instance = new TaxisDAO();
        instance.setConnection(dbConnect);
        Taxis obj = new Taxis(0, "TestImma", "TestDIESEL", 12, "Test Description");
        Taxis expResult = instance.create(obj);
        idTaxi = expResult.getIdTaxi();
        Taxis result = instance.read(idTaxi);
        assertEquals("ID différentes.", expResult.getIdTaxi(), result.getIdTaxi());
        assertEquals("Imatriculations différentes.", expResult.getImmatriculation(), result.getImmatriculation());
        assertEquals("Carburants différents.", expResult.getCarburant(), result.getCarburant());
        assertEquals("Prixs différents.", expResult.getPrixKm(), result.getPrixKm(), 0);
        assertEquals("ID non générée.", expResult.getIdTaxi(), result.getIdTaxi());

        try {
            result = instance.read(0);
            fail("Id non générée.");
        } catch (SQLException e) {
        }
        instance.delete(result);
    }

    /**
     * Test of rechDescription method, of class TaxisDAO.
     */
    @Test
    public void testRechDescription() throws Exception {
        System.out.println("Recherche par description");
        String motrech = "TestDescription";
        Taxis obj1 = new Taxis(0, "TestImma", "TestDIESEL", 12, "TestDescription");
        Taxis obj2 = new Taxis(0, "TestImma2", "TestCarburant2", 15, "TestDescription");
        TaxisDAO instance = new TaxisDAO();
        instance.setConnection(dbConnect);
        obj1 = instance.create(obj1);
        obj2 = instance.create(obj2);

        List<Taxis> result = instance.rechDescription(motrech);
        if (result.indexOf(obj1) < 0) {
            fail("Taxi non trouvé. " + obj1);
        }
        if (result.indexOf(obj2) < 0) {
            fail("Taxi non trouvé." + obj2);
        }
        instance.delete(obj1);
        instance.delete(obj2);
    }

    /**
     * Test of update method, of class TaxisDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Taxis obj = new Taxis(0, "TestImma", "TestDIESEL", 12, "Test Description");
        TaxisDAO instance = new TaxisDAO();
        instance.setConnection(dbConnect);

        obj = instance.create(obj);
        obj.setImmatriculation("TestImma2");
        obj.setCarburant("TestCarburant2");
        obj.setPrixKm(15);
        obj.setDescription("TestDescri2");

        Taxis expResult = obj;
        Taxis result = instance.update(obj);
        assertEquals(expResult.getIdTaxi(), result.getIdTaxi());
        assertEquals(expResult.getImmatriculation(), result.getImmatriculation());
        assertEquals(expResult.getCarburant(), result.getCarburant());
        assertEquals(expResult.getPrixKm(), result.getPrixKm(), 0);
        assertEquals(expResult.getDescription(), result.getDescription());
        // TODO review the generated test code and remove the default call to fail.
        instance.update(obj);
    }

    /**
     * Test of delete method, of class TaxisDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Taxis obj = new Taxis(0, "TestImma", "TestDIESEL", 12, "Test Description");
        TaxisDAO instance = new TaxisDAO();
        instance.setConnection(dbConnect);
        instance.create(obj);
        instance.delete(obj);
        try {
            instance.read(obj.getIdTaxi());
            fail("Taxi introuvable.");
        } catch (SQLException e) {
        }
    }

}
