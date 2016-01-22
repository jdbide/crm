package miage.pds.api.ctruong.uc.prospect.suggest.controller;

import miage.pds.api.ctruong.uc.prospect.suggest.dao.UserDAO;
import miage.pds.api.ctruong.uc.prospect.suggest.model.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * This is a class dao which declare all method communicate with the user collection in the database crm.
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public class UserDAOImpl extends BasicDAO<User, ObjectId> implements UserDAO {

    private static final String LOGIN   = "login";
    private static final String ID      = "_id";

    /**
     * Constructor DAO
     * @param entityClass
     * @param ds
     */
    public UserDAOImpl(Class<User> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    /**
     * Method to call all users in the table
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        Query<User> query = createQuery().order(ID);
        return query.asList();
    }
}
