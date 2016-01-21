package prospect.dao;

import com.mongodb.MongoClient;
import miage.pds.prospect.controller.UserClientRelationDAOImpl;
import miage.pds.prospect.model.UserClientRelation;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

import static org.junit.Assert.*;

/**
 * The unit test for the class user client relationship dao
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public class UserClientRelationDAOImplTest {
    private MongoClient                 mongoClient;
    private Morphia                     morphia;
    private UserClientRelationDAOImpl   userClientRelationDAO;
    private final String                dbname      = "crm";
    private Datastore                   datastore;

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
        List<UserClientRelation> clientRelationList = userClientRelationDAO.createQuery().field("idUser").equal(1).asList();
        assertEquals(clientRelationList.size(), userClientRelationDAO.getClientByUserId(1).size());
    }

    @Test
    public void testGetUserByClientId() throws Exception {
        List<UserClientRelation> userRelationList   = userClientRelationDAO.createQuery().field("idClient").equal(1).asList();
        assertEquals(userRelationList.size(), userClientRelationDAO.getUserByClientId(1).size());
    }

    @Test
    public void testCountRelationshipByClientID() throws Exception {
        List<UserClientRelation> userClientRelations= userClientRelationDAO.createQuery().field("idClient").equal(1).asList();
        assertEquals(userClientRelations.size(), userClientRelationDAO.countRelationshipByClientID(1));
    }

    @Test
    public void testCheckRelation() throws Exception {
        assertTrue(userClientRelationDAO.checkRelation(1,1));
    }
}
