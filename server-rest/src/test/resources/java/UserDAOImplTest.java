/**
 * The unit test for the class user dao
 * <p>
 * Created by Truong on 12/20/2015.
 *
 * @version 1.1.19
 * @serial 111912202015
 */
/* public class UserDAOImplTest {
    private final static Logger log         = LoggerFactory.getLogger(UserDAOImplTest.class);
    private MongoClient         mongoClient;
    private Morphia             morphia;
    private final String        dbname      = "crm";
    private Datastore           datastore;
    private UserDAOImpl         userDAO;

    @Before
    public void setUp() throws Exception {
        this.mongoClient    = new MongoClient();
        this.morphia        = new Morphia();
        this.morphia.map(User.class);
        this.datastore      = this.morphia.createDatastore(mongoClient, dbname);
        this.userDAO        = new UserDAOImpl(User.class, datastore);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = userDAO.createQuery().asList();
        assertEquals(users.size(), userDAO.getAllUsers().size());
    }
} */
