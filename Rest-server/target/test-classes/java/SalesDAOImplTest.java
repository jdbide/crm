import com.mongodb.MongoClient;
import miage.pds.prospect.controller.SalesDAOImpl;
import miage.pds.prospect.model.Sales;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Truong on 12/26/2015.
 */
public class SalesDAOImplTest {

    private final static Logger log         = LoggerFactory.getLogger(SalesDAOImplTest.class);
    private MongoClient         mongoClient;
    private Morphia             morphia;
    private SalesDAOImpl        salesDAO;
    private final String        dbname      = "crm";
    private Datastore           datastore;

    @Before
    public void setUp() throws Exception {
        this.mongoClient    = new MongoClient();
        this.morphia        = new Morphia();
        this.morphia.map(Sales.class);
        this.datastore      = this.morphia.createDatastore(mongoClient, dbname);
        salesDAO            = new SalesDAOImpl(Sales.class, datastore);
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
        int count = sales.size();
        log.info("The size: " + count);
        assertEquals(count, salesDAO.getCountAllSales());
    }

    @Test
    public void testGetSalesByIDClient() throws Exception {

    }
}
