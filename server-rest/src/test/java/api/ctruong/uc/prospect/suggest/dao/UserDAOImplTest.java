package api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.controller.UserDAOImpl;
import miage.pds.api.ctruong.uc.prospect.suggest.dao.UserDAO;
import miage.pds.api.ctruong.uc.prospect.suggest.model.User;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * The unit test for the class user dao
 * <p>
 * Created by Truong on 12/20/2015.
 *
 * @version 1.1.19
 * @serial 111912202015
 */
public class UserDAOImplTest {
    private final static Logger log         = LoggerFactory.getLogger(UserDAOImplTest.class);
    private MongoService mongoService;
    private UserDAO userDAO;
    private Datastore datastore;

    @Before
    public void setUp() throws Exception {
        this.mongoService   = new MongoService();
        this.datastore      = mongoService.getDatastore();
        this.userDAO        = new UserDAOImpl(User.class, datastore);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = userDAO.getAllUsers();
        assertEquals(100, userDAO.getAllUsers().size());
    }
}
