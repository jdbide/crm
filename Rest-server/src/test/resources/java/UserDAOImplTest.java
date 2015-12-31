import com.mongodb.MongoClient;
import miage.pds.prospect.controller.UserDAOImpl;
import miage.pds.prospect.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Truong on 12/26/2015.
 */
public class UserDAOImplTest {
    private final static Logger log         = LoggerFactory.getLogger(UserDAOImplTest.class);
    private MongoClient mongoClient;
    private Morphia morphia;
    private final String        dbname      = "crm";
    private Datastore datastore;
    private UserDAOImpl userDAO;

    @Before
    public void setUp() throws Exception {
        this.mongoClient = new MongoClient();
        this.morphia = new Morphia();
        this.morphia.map(User.class);
        this.datastore = this.morphia.createDatastore(mongoClient, dbname);
        this.userDAO = new UserDAOImpl(User.class, datastore);
    }

    @Test
    public void testGetAllUsers() throws Exception {

    }
}
