import com.mongodb.MongoClient;
import miage.pds.prospect.controller.UserClientRelationDAOImpl;
import miage.pds.prospect.model.UserClientRelation;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import static org.junit.Assert.*;

/**
 * Created by Truong on 12/26/2015.
 */
public class UserClientRelationDAOImplTest {
    private MongoClient mongoClient;
    private Morphia morphia;
    private UserClientRelationDAOImpl userClientRelationDAO;
    private final String        dbname      = "crm";
    private Datastore datastore;

    @Before
    public void setUp() throws Exception {
        this.mongoClient    = new MongoClient();
        this.morphia        = new Morphia();
        this.morphia.map(UserClientRelation.class);
        this.datastore      = this.morphia.createDatastore(mongoClient,dbname);
        this.userClientRelationDAO = new UserClientRelationDAOImpl(UserClientRelation.class, datastore);
    }

    @Test
    public void testGetClientByUserId() throws Exception {

    }

    @Test
    public void testGetUserByClientId() throws Exception {

    }
}
