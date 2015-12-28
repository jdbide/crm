package miage.pds.prospect.controller;

import miage.pds.prospect.dao.UserClientRelationDAO;
import miage.pds.prospect.model.UserClientRelation;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by Truong on 12/22/2015.
 */
public class UserClientRelationDAOImpl extends BasicDAO<UserClientRelation, ObjectId> implements UserClientRelationDAO{

    private static final String CLIENT_ID   = "idClient";
    private static final String USER_ID     = "idUser";

    /**
     *
     * @param entityClass
     * @param ds
     */
    public UserClientRelationDAOImpl(Class<UserClientRelation> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    /**
     *
     * @param idUser
     * @return
     */
    @Override
    public List<UserClientRelation> getClientByUserId(int idUser) {
        Query<UserClientRelation> query = createQuery().field(USER_ID).equal(idUser);
        return query.asList();
    }

    /**
     *
     * @param idClient
     * @return
     */
    @Override
    public List<UserClientRelation> getUserByClientId(int idClient) {
        Query<UserClientRelation> query = createQuery().field(CLIENT_ID).equal(idClient);
        return query.asList();
    }

    @Override
    public long countRelationshipByClientID(int idClient) {
        Query<UserClientRelation> query = createQuery().field(CLIENT_ID).equal(idClient);
        long i = count(query);
        return i;
    }
}
