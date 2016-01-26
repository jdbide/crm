package api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.controller.SalesDAOImpl;
import miage.pds.api.ctruong.uc.prospect.suggest.controller.UserClientRelationDAOImpl;
import miage.pds.api.ctruong.uc.prospect.suggest.mock.MockTable;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Sales;
import miage.pds.api.ctruong.uc.prospect.suggest.model.UserClientRelation;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

/**
 * The unit test for the class sales dao
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public class SalesDAOImplTest {

    private final static Logger log         = LoggerFactory.getLogger(SalesDAOImplTest.class);
    private MongoService        service;
    private SalesDAOImpl        salesDAO;
    private final String        dbname      = "crm";
    private Datastore           datastore;
    private MockTable           mockTable;
    private UserClientRelationDAOImpl userClientRelationDAO;

    @Before
    public void setUp() throws Exception {
        this.service    = new MongoService();
        this.datastore      = service.getDatastore();
        this.salesDAO            = new SalesDAOImpl(Sales.class, datastore);
        this.mockTable      = new MockTable();
        mockTable.mockRelationAndSalesTable();
        this.userClientRelationDAO = new UserClientRelationDAOImpl(UserClientRelation.class, datastore);
    }


    @Test
    public void testGetAllSales() throws Exception {
        List<Sales> sales = salesDAO.createQuery().asList();
        assertEquals(sales.size(), salesDAO.getAllSales().size());
        assertTrue(sales.size() == salesDAO.getAllSales().size());
    }


    @Test
    public void testGetCountAllSales() throws Exception {
        List<Sales> sales = salesDAO.createQuery().asList();
        int         count = sales.size();
        log.info("The size: " + count);
        assertEquals(count, salesDAO.getCountAllSales());
    }

    @Test
    public void testGetSalesByIDClient() throws Exception {
        int     idClient = 1;
        List<Sales> sales= salesDAO.createQuery().field("idClient").equal(idClient).asList();
        assertNotNull(sales);
        assertEquals(sales.size(), salesDAO.getSalesByIDClient(idClient).size());
    }

    @After
    public void tearDown() throws Exception {
        salesDAO.deleteByQuery(salesDAO.createQuery());
        userClientRelationDAO.deleteByQuery(userClientRelationDAO.createQuery());
    }
}
