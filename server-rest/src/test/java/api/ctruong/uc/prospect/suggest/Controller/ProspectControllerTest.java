package api.ctruong.uc.prospect.suggest.Controller;

import com.mongodb.MongoClient;
import miage.pds.api.ctruong.uc.prospect.suggest.controller.*;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Sales;
import miage.pds.api.ctruong.uc.prospect.suggest.model.User;
import miage.pds.api.ctruong.uc.prospect.suggest.model.UserClientRelation;
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
    private ProspectController prospectController;
    private SalesDAOImpl salesDAO;
    private UserDAOImpl userDAO;
    private final String                dbname      = "crm";
    private Datastore                   datastore;
    private ProspectDAOImpl prospectDAO;
    private UserClientRelationDAOImpl userClientRelationDAO;


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
            List<Prospect>                  prospectListl= prospectDAO.getAllProspect();
            for (Prospect prospect: prospectListl){
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
        assertEquals(100, userMap.size());
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
        assertEquals(100, userMap.size());
    }

    @Test
    public void testAnalyzeProspectByRelationLv() throws Exception {
        HashMap<User, ArrayList<Prospect>> hashMap = init();
        HashMap<User, ArrayList<Prospect>> userMap = prospectController.analyzeProspectByRelationLv(hashMap);
        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userMap.entrySet().iterator();
        assertEquals(100, userMap.size());
    }

    @Test
    public void testAnalyzeProspectByPlaceNumber() throws Exception {
        HashMap<User, ArrayList<Prospect>> hashMap = init();
        HashMap<User, ArrayList<Prospect>> userMap = prospectController.analyzeProspectByPlaceNumber(hashMap);
        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userMap.entrySet().iterator();
        assertEquals(100, userMap.size());
    }

    @Test
    public void testGetSalesAverage() throws Exception {
        List<Sales> salesList = salesDAO.getAllSales();
        int         count     = salesList.size();
        double      sum       = 0.0d;
        log.info("check sum: " + sum);
        for (Sales sales : salesList){
            sum = sum + sales.getValue();
        }
        double      average   = sum /count;
        assertTrue(average == prospectController.getSalesAverage());
    }
}