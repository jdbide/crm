package api.jbide.uc.registercall;

import static junit.framework.Assert.assertEquals;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.jbide.uc.registercall.dao.DAO;
import miage.pds.api.jbide.uc.registercall.model.Cra;
import miage.pds.api.tlacouque.uc.admin.ref.customer.controller.RestCustomerController;
/**
 * Created by jbide on 22/01/2016.
 */
public class CraRestControllerTest {
    //private MockHttpServletRequest request;
    //private MockHttpServletResponse response;
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

        this.datastore      = MongoDatastoreConfig.getDataStore();
       
         }

    	DAO dao = new DAO();
            @Test
       public void testCreateCra() throws Exception {
            	Cra cra = new Cra();
            	cra.setIduser("id1");
            	int size0 = dao.getListCraForUser("id1").size();
            	dao.createCra(cra);
            	int size1 = dao.getListCraForUser("id1").size();	
                assertEquals(size0+1,size1);
                dao.dropTables();
   }
            @Test
        	public void getListCraForUserTest() {
            	Cra cra = new Cra();
            	cra.setIduser("id1");
            	cra.setClientname("testclient");
            	int size0 = dao.getListCraForUser("id1").size();
            	dao.createCra(cra);
            	cra.setIduser("id2");
            	dao.createCra(cra);
        		assertEquals(dao.getListCraForUser("id1").size(),dao.datastore.createQuery(Cra.class).field("iduser").equal("id1").asList().size());
        		dao.dropTables();
            }

}
