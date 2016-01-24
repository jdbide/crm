package api.ctruong.uc.prospect.suggest.dao;

import com.mongodb.MongoClient;
import miage.pds.api.ctruong.uc.prospect.suggest.controller.ProspectDAOImpl;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * The unit test for the class prospect dao
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public class ProspectDAOImplTest {
/*    private final static Logger log         = LoggerFactory.getLogger(ProspectDAOImplTest.class);
    private MongoClient         mongoClient;
    private Morphia             morphia;
    private ProspectDAOImpl     prospectDAO;
    private final String        dbname      = "crm";
    private Datastore           datastore;


    @Before
    public void setUp() throws Exception {
        this.morphia        = new Morphia();
        this.morphia.map(Prospect.class);
        this.mongoClient    = new MongoClient();
        this.datastore      = this.morphia.createDatastore(mongoClient,dbname);
        prospectDAO         = new ProspectDAOImpl(Prospect.class, datastore);

    }


    @Test
    public void testGetAllProspect() throws Exception {
        List<Prospect> prospects = prospectDAO.createQuery().asList();
        assertEquals(prospects.size(), prospectDAO.getAllProspect().size());
    }

    @Test
    public void testGetProspectByID() throws Exception {
        Prospect prospect   = prospectDAO.createQuery().field("id").equal(1).get();
        Prospect prospect1  = prospectDAO.getProspectByID(1);
        assertEquals(prospect.getPlace(), prospect1.getPlace());
    } */
}
