package api.ctruong.uc.prospect.suggest.Controller;

import miage.pds.api.ctruong.uc.prospect.suggest.controller.*;
import miage.pds.api.ctruong.uc.prospect.suggest.mock.MockTable;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Sales;
import miage.pds.api.ctruong.uc.prospect.suggest.model.User;
import miage.pds.api.ctruong.uc.prospect.suggest.model.UserClientRelation;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mongodb.morphia.Datastore;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

/**
 * The unit test for the class prospect controller
 * <p/>
 * Created by Truong on 12/20/2015.
 *
 * @version 1.1.19
 * @serial 111912202015
 */
@RunWith(MockitoJUnitRunner.class)
@PrepareForTest({ProspectController.class, Logger.class, ProspectDAOImpl.class, UserDAOImpl.class, UserClientRelationDAOImpl.class, SalesDAOImpl.class})
public class ProspectControllerTest {
    private static final Logger log = LoggerFactory.getLogger(ProspectControllerTest.class);
    private MongoService service;
    private ProspectController prospectController;
    private SalesDAOImpl salesDAO;
    private UserDAOImpl userDAO;
    private Datastore datastore;
    private ProspectDAOImpl prospectDAO;
    private UserClientRelationDAOImpl userClientRelationDAO;
    private MockTable mockTable;


    @Before
    public void setUp() throws Exception {
        this.prospectController = new ProspectController();
        this.service = new MongoService();
        this.datastore = service.getDatastore();
        this.salesDAO = new SalesDAOImpl(Sales.class, datastore);
        this.userDAO = new UserDAOImpl(User.class, datastore);
        this.prospectDAO = new ProspectDAOImpl(Prospect.class, datastore);
        this.userClientRelationDAO = new UserClientRelationDAOImpl(UserClientRelation.class, datastore);
        this.mockTable = new MockTable();
        if (userDAO.count() > 0 && prospectDAO.count() > 0) {
            log.debug("Ok it's good, let begins all the test");
        } else {
            mockTable.mockClientTable();
            mockTable.mockRelationAndSalesTable();
            mockTable.mockUserTable();
        }
        PowerMockito.mockStatic(ProspectController.class);
        PowerMockito.mockStatic(Logger.class);
        PowerMockito.mockStatic(ProspectDAOImpl.class);
        PowerMockito.mockStatic(UserDAOImpl.class);
        PowerMockito.mockStatic(UserClientRelationDAOImpl.class);
        PowerMockito.mockStatic(SalesDAOImpl.class);

        Logger logger = mock(Logger.class);
        doNothing().when(logger).info(anyString());
        PowerMockito.when(LoggerFactory.getLogger(anyString())).thenReturn(logger);
    }

    public HashMap<User, ArrayList<Prospect>> init() {

        HashMap<User, ArrayList<Prospect>> hashMap = new HashMap<User, ArrayList<Prospect>>();
        List<User> list = userDAO.getAllUsers();
        for (User user : list) {
            List<Prospect> prospectListl = prospectDAO.getAllProspect();
            ArrayList<Prospect> arrayList = new ArrayList<Prospect>();
            for (Prospect prospect : prospectListl) {
                if (!userClientRelationDAO.checkRelation(user.getId(), prospect.getId())) {
                    arrayList.add(prospect);
                }
            }
            hashMap.put(user, arrayList);
        }

        return hashMap;
    }

//    @Test
//    public void testAnalyseProspect() throws Exception {
//        HashMap<User, ArrayList<Prospect>> hashMap = init();
//        HashMap<User, ArrayList<Prospect>> userMap = prospectController.analyseProspect();
//        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userMap.entrySet().iterator();
//        while (entryIterator.hasNext()){
//            Map.Entry<User, ArrayList<Prospect>> entry = entryIterator.next();
//            ArrayList<Prospect> prospects = entry.getValue();
//            assertEquals(1, prospects.size());
//            assertNotNull(prospects);
//        }
//        assertEquals(100, userMap.size());
//    }

    @Test
    public void testGetSalesAverage() throws Exception {
        List<Sales> salesList = salesDAO.getAllSales();
        int count = salesList.size();
        double sum = 0.0d;
        log.info("check sum: " + sum);
        for (Sales sales : salesList) {
            sum = sum + sales.getValue();
        }
        double average = sum / count;
        assertTrue(average == prospectController.getSalesAverage());
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
        assertNotNull(userMap);
    }

    @Test
    public void testAnalyzeProspectByRelationLv() throws Exception {
        HashMap<User, ArrayList<Prospect>> hashMap = init();
        HashMap<User, ArrayList<Prospect>> userMap = prospectController.analyzeProspectByRelationLv(hashMap);
        assertNotNull(userMap);
    }

    @Test
    public void testAnalyzeProspectByPlaceNumber() throws Exception {
        HashMap<User, ArrayList<Prospect>> hashMap = init();
        HashMap<User, ArrayList<Prospect>> userMap = prospectController.analyzeProspectByPlaceNumber(hashMap);
        assertNotNull(userMap);
    }


}