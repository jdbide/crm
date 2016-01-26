package api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.controller.UserClientRelationDAOImpl;
import miage.pds.api.ctruong.uc.prospect.suggest.model.UserClientRelation;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * The unit test for the class user client relationship dao
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public class UserClientRelationDAOImplTest {
    private MongoService service;
    private UserClientRelationDAOImpl   userClientRelationDAO;
    private Datastore                   datastore;

    @Before
    public void setUp() throws Exception {
        this.service    = new MongoService();
        this.datastore  = service.getDatastore();
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
        assertFalse(userClientRelationDAO.checkRelation(1,1));
    }
}
