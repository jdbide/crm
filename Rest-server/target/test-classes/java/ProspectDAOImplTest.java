import com.mongodb.MongoClient;
import miage.pds.prospect.controller.ProspectDAOImpl;
import miage.pds.prospect.model.Prospect;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Truong on 12/26/2015.
 */
public class ProspectDAOImplTest {
    private final static Logger log         = LoggerFactory.getLogger(ProspectDAOImplTest.class);
    private MongoClient         mongoClient;
    private Morphia             morphia;
    private ProspectDAOImpl     prospectDAO;
    private final String        dbname      = "crm";
    private Datastore           datastore;

    @Before
    public void setUp() throws Exception {
        this.mongoClient    = new MongoClient();
        this.morphia        = new Morphia();
        this.morphia.map(Prospect.class);
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
        Prospect prospect   = prospectDAO.getProspectByID(1);
        assertNotNull(prospect);
        assertEquals(1, prospect.getId());
        assertEquals(522, prospect.getPlace());
    }
}
