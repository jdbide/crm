package api.ctruong.uc.prospect.suggest.Controller;

import miage.pds.api.ctruong.uc.prospect.suggest.controller.*;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Sales;
import miage.pds.api.ctruong.uc.prospect.suggest.model.User;
import miage.pds.api.ctruong.uc.prospect.suggest.model.UserClientRelation;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.junit.Assert.*;

/**
 * The unit test for the class prospect controller
 * <p>
 * Created by Truong on 12/20/2015.
 *
 * @version 1.1.19
 * @serial 111912202015
 */
public class ProspectControllerTest {
    private static final Logger log = LoggerFactory.getLogger(ProspectControllerTest.class);
    private MongoService service;
    private ProspectController prospectController;
    private SalesDAOImpl salesDAO;
    private UserDAOImpl userDAO;
    private final String                dbname      = "crm";
    private Datastore datastore;
    private ProspectDAOImpl prospectDAO;
    private UserClientRelationDAOImpl userClientRelationDAO;


    @Before
    public void setUp() throws Exception {
        this.prospectController     = new ProspectController();
        this.service                = new MongoService();
        this.datastore              = service.getDatastore();
        this.salesDAO               = new SalesDAOImpl(Sales.class, datastore);
        this.userDAO                = new UserDAOImpl(User.class, datastore);
        this.prospectDAO            = new ProspectDAOImpl(Prospect.class, datastore);
        this.userClientRelationDAO  = new UserClientRelationDAOImpl(UserClientRelation.class, datastore);
    }

    public HashMap<User, ArrayList<Prospect>> init(){
        HashMap<User, ArrayList<Prospect>>  hashMap     = new HashMap<User, ArrayList<Prospect>>();
        List<User>                          list        = userDAO.getAllUsers();
        for (User user: list){
            List<Prospect> prospectListl= prospectDAO.getAllProspect();
            ArrayList<Prospect>                 arrayList   = new ArrayList<Prospect>();
            for (Prospect prospect: prospectListl){
                if (!userClientRelationDAO.checkRelation(user.getId(), prospect.getId())){
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
        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userMap.entrySet().iterator();
        while (entryIterator.hasNext()){
            Map.Entry<User, ArrayList<Prospect>> entry = entryIterator.next();
            ArrayList<Prospect> prospects = entry.getValue();
            assertEquals(1, prospects.size());
            assertNotNull(prospects);
        }
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
        assertEquals(100, userMap.size());
    }

    @Test
    public void testAnalyzeProspectByRelationLv() throws Exception {
        HashMap<User, ArrayList<Prospect>> hashMap = init();
        HashMap<User, ArrayList<Prospect>> userMap = prospectController.analyzeProspectByRelationLv(hashMap);
        assertEquals(100, userMap.size());
    }

    @Test
    public void testAnalyzeProspectByPlaceNumber() throws Exception {
        HashMap<User, ArrayList<Prospect>> hashMap = init();
        HashMap<User, ArrayList<Prospect>> userMap = prospectController.analyzeProspectByPlaceNumber(hashMap);
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