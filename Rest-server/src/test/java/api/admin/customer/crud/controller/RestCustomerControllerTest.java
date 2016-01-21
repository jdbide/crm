package api.admin.customer.crud.controller;

import com.mongodb.MongoClient;
import miage.pds.api.admin.customer.crud.controller.RestCustomerController;
import miage.pds.api.admin.customer.crud.createhc.dao.HealthCenterDAO;
import miage.pds.api.admin.customer.crud.createhc.entities.HealthCenter;
import miage.pds.api.admin.customer.crud.createindep.dao.IndependantDAO;
import miage.pds.api.admin.customer.crud.createindep.entities.Independant;
import miage.pds.api.admin.customer.crud.message.MessageRestCustomer;
import miage.pds.api.admin.customer.crud.message.ResponseRestCustomer;
import miage.pds.prospect.model.Prospect;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.UnknownHostException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by tlacouque on 21/01/2016.
 */

public class RestCustomerControllerTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    RestCustomerController restCustomerController;
 /**   @Autowired
         private HandlerAdapter handlerAdapter;

         @Autowired
       private HandlerMapping handlerMapping;
*/

 MongoClient mongoClient;
    Morphia morphia;
    Datastore datastore;
    String dbName = "crm";

    @Before
      public void setUp() throws UnknownHostException {
            request = new MockHttpServletRequest();
             response = new MockHttpServletResponse();
        this.mongoClient    = new MongoClient("192.168.20.3",8071);
        this.morphia        = new Morphia();
        this.morphia.map(HealthCenter.class);
        this.datastore      = this.morphia.createDatastore(mongoClient,dbName);


        restCustomerController = new RestCustomerController();

         }


            @Test
       public void testCreateHC() throws Exception {
                HealthCenter healthCenter = new HealthCenter();
                healthCenter.setSiretNumber(1L);
                healthCenter.setName("TestHc");

                MessageRestCustomer messageRestCustomer = new MessageRestCustomer(1,healthCenter);
                List<HealthCenter> list= new HealthCenterDAO(datastore).find().asList();
                int nbHCbeforeTest = new HealthCenterDAO(datastore).find().asList().size();
                nbHCbeforeTest = nbHCbeforeTest +1;
                ResponseRestCustomer responseRestCustomer = restCustomerController.createHealthCenter(messageRestCustomer);
                int nbHCAfterTest = new HealthCenterDAO(datastore).find().asList().size();
                new HealthCenterDAO(datastore).delete(healthCenter);
                assertEquals(nbHCbeforeTest,nbHCAfterTest);
                assertTrue(responseRestCustomer.getIsInserted());
   }

    @Test
    public void testCreateIndep() throws Exception {
        Independant independant = new Independant();
        independant.setSiretNumber(1L);
        independant.setName("TestIndep");
        MessageRestCustomer messageRestCustomer = new MessageRestCustomer();
        messageRestCustomer.setIndependant(independant);
        messageRestCustomer.setIdUser(1);
        int nbIndepbeforeTest = new IndependantDAO(datastore).find().asList().size();
        nbIndepbeforeTest = nbIndepbeforeTest +1;
        restCustomerController.createIndependant(messageRestCustomer);
        int nbIndepAfterTest = new IndependantDAO(datastore).find().asList().size();
        new IndependantDAO(datastore).delete(independant);
        assertEquals(nbIndepbeforeTest,nbIndepAfterTest);
    }

    @Test
    public void testGetHolding() throws Exception {
        ResponseRestCustomer responseRestCustomer = restCustomerController.getHoldings();
        assertNotNull(responseRestCustomer.getHoldings());
    }

    @Test
    public void testGetPurchasingCentrals() throws Exception {
        ResponseRestCustomer responseRestCustomer = restCustomerController.getPurchasingCentrals();
        assertNotNull(responseRestCustomer.getPurchasingCentrals());
    }

    @Test
    public void testGetCompanies() throws Exception {
        ResponseRestCustomer responseRestCustomer = restCustomerController.getCompanies();
        assertNotNull(responseRestCustomer.getCompanies());
    }

    @Test
    public void testGetSpecialty() throws Exception {
        ResponseRestCustomer responseRestCustomer = restCustomerController.getSpecialties();
        assertNotNull(responseRestCustomer.getSpecialties());
    }

    @Test
    public void testGetHealthCenters() throws Exception {
        int healthCentersNumber = new HealthCenterDAO(datastore).findAllWithoutUserId(1).size();
        ResponseRestCustomer responseRestCustomer = restCustomerController.getHealthCenters(1);
        assertEquals(healthCentersNumber,responseRestCustomer.getHealthCenters().size());
    }

    @Test
    public void testGetIndependants() throws Exception {
        int independantNumber = new IndependantDAO(datastore).findAllWithoutUserId(1).size();
        ResponseRestCustomer responseRestCustomer = restCustomerController.getIndependants(1);
        assertEquals(independantNumber,responseRestCustomer.getIndependants().size());
    }

    @Test
    public void testGetCustomers() throws Exception {
        int independantNumber = new IndependantDAO(datastore).findAllWithoutUserId(1).size();
        int healthCentersNumber = new HealthCenterDAO(datastore).findAllWithoutUserId(1).size();
        ResponseRestCustomer responseRestCustomer = restCustomerController.getCustomers(1);
        assertEquals(healthCentersNumber,responseRestCustomer.getHealthCenters().size());
        assertEquals(independantNumber,responseRestCustomer.getIndependants().size());
    }
}
