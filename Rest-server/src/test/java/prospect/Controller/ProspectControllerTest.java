package prospect.Controller;

import com.mongodb.MongoClient;
import miage.pds.prospect.controller.*;
import miage.pds.prospect.model.Prospect;
import miage.pds.prospect.model.Sales;
import miage.pds.prospect.model.User;
import miage.pds.prospect.model.UserClientRelation;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;

import java.util.*;

/**
 * The unit test for the class prospect controller
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public class ProspectControllerTest {
    private static final Logger         log = LoggerFactory.getLogger(ProspectControllerTest.class);
    private MongoClient                 mongoClient;
    private Morphia                     morphia;
    private ProspectController          prospectController;
    private SalesDAOImpl                salesDAO;
    private UserDAOImpl                 userDAO;
    private final String                dbname      = "crm";
    private Datastore                   datastore;
    private ProspectDAOImpl             prospectDAO;
    private UserClientRelationDAOImpl   userClientRelationDAO;


    @Before
    public void setUp() throws Exception {
        this.prospectController     = new ProspectController();
        this.mongoClient            = new MongoClient();
        this.morphia                = new Morphia();
        this.datastore              = morphia.createDatastore(mongoClient, dbname);
        this.salesDAO               = new SalesDAOImpl(Sales.class, datastore);
        this.userDAO                = new UserDAOImpl(User.class, datastore);
        this.prospectDAO            = new ProspectDAOImpl(Prospect.class, datastore);
        this.userClientRelationDAO  = new UserClientRelationDAOImpl(UserClientRelation.class, datastore);
    }

    public HashMap<User, ArrayList<Prospect>> init(){
        HashMap<User, ArrayList<Prospect>>  hashMap     = new HashMap<User, ArrayList<Prospect>>();
        List<User>                          list        = userDAO.getAllUsers();
        for (User user: list){
            ArrayList<Prospect>                 arrayList   = new ArrayList<Prospect>();
            List<Prospect>                  prospectList= prospectDAO.getAllProspect();
            for (Prospect prospect: prospectList){
                if (userClientRelationDAO.checkRelation(user.getId(), prospect.getId()) == false){
                    arrayList.add(prospect);
                }
            }
            hashMap.put(user, arrayList);
        }

        return hashMap;
    }

    @Test
    public void testAnalyseProspect() throws Exception {
        HashMap<User, ArrayList<Prospect>> hashMap = init();
        HashMap<User, ArrayList<Prospect>> userMap = prospectController.analyseProspect();
        assertEquals(10, userMap.size());
        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userMap.entrySet().iterator();
        while (entryIterator.hasNext()){
            Map.Entry<User, ArrayList<Prospect>> userArrayListEntry  = entryIterator.next();
            User                                 user                = userArrayListEntry.getKey();
            ArrayList<Prospect>                  prospects           = userArrayListEntry.getValue();
            for (Prospect prospect: prospects){
                if (user.getId() == 3){
                    assertEquals(1, prospect.getId());
                }
                if (user.getId() == 2){
                    assertEquals(4, prospect.getId());
                }
            }
        }
    }

    @Test
    public void testGetProspectListForEachUser() throws Exception {
        HashMap<User, ArrayList<Prospect>> hashMap = init();
        assertEquals(hashMap.size(), prospectController.getProspectListForEachUser().size());
    }

    @Test
    public void testAnalyzeProspectBySales() throws Exception {
        HashMap<User, ArrayList<Prospect>> hashMap = init();
        HashMap<User, ArrayList<Prospect>> userMap = prospectController.analyzeProspectBySales(hashMap);
        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userMap.entrySet().iterator();
        while (entryIterator.hasNext()){
            Map.Entry<User, ArrayList<Prospect>> userArrayListEntry  = entryIterator.next();
            User                                 user                = userArrayListEntry.getKey();
            ArrayList<Prospect>                  prospects           = userArrayListEntry.getValue();
            if (user.getId() == 1){
                assertEquals(1, prospects.size());
                for (Prospect prospect: prospects){
                    assertEquals(4, prospect.getId());
                    assertEquals(1234, prospect.getPlace());
                }
            }

            if (user.getId() == 3){
                assertEquals(0, prospects.size());
            }
        }
    }

    @Test
    public void testAnalyzeProspectByRelationLv() throws Exception {
        HashMap<User, ArrayList<Prospect>> hashMap = init();
        HashMap<User, ArrayList<Prospect>> userMap = prospectController.analyzeProspectByRelationLv(hashMap);
        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userMap.entrySet().iterator();
        while (entryIterator.hasNext()){
            Map.Entry<User, ArrayList<Prospect>> userArrayListEntry  = entryIterator.next();
            User                                 user                = userArrayListEntry.getKey();
            ArrayList<Prospect>                  prospects           = userArrayListEntry.getValue();
            if (user.getId() == 3){
                assertEquals(2, prospects.size());
                for (Prospect prospect: prospects){
                    if (prospect.getId() == 7){
                        assertEquals(14134, prospect.getPlace());
                    }
                    if (prospect.getId() == 3) {
                        assertEquals(1547, prospect.getPlace());
                    }
                }
            }
        }
    }

    @Test
    public void testAnalyzeProspectByPlaceNumber() throws Exception {
        HashMap<User, ArrayList<Prospect>> hashMap = init();
        HashMap<User, ArrayList<Prospect>> userMap = prospectController.analyzeProspectByPlaceNumber(hashMap);
        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userMap.entrySet().iterator();
        while (entryIterator.hasNext()){
            Map.Entry<User, ArrayList<Prospect>> userArrayListEntry  = entryIterator.next();
            User                                 user                = userArrayListEntry.getKey();
            ArrayList<Prospect>                  prospects           = userArrayListEntry.getValue();
            assertEquals(1, prospects.size());
        }
    }

    @Test
    public void testGetSalesAverage() throws Exception {
        List<Sales> salesList = salesDAO.getAllSales();
        int         count     = salesList.size();
        double      sum       = 0.0d;
        for (Sales sales : salesList){
            sum = sum + sales.getValue();
        }
        double      average   = sum /count;
        assertTrue(average == prospectController.getSalesAverage());
    }
}