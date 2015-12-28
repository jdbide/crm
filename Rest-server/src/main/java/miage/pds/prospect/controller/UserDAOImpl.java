package miage.pds.prospect.controller;

import miage.pds.prospect.dao.UserDAO;
import miage.pds.prospect.model.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by Truong on 12/22/2015.
 */
public class UserDAOImpl extends BasicDAO<User, ObjectId> implements UserDAO {

    private static final String LOGIN = "login";

    public UserDAOImpl(Class<User> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    @Override
    public User getUserByLogin(String login) {
        Query<User> query = createQuery().field(LOGIN).equal(login);
        return query.get();
    }

    @Override
    public List<User> getUsers() {
        Query<User> query = createQuery();
        return query.asList();
    }
}