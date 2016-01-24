package miage.pds.api.ctruong.uc.prospect.suggest.controller;

import miage.pds.api.ctruong.uc.prospect.suggest.model.UserClientRelation;
import miage.pds.api.ctruong.uc.prospect.suggest.dao.UserClientRelationDAO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * This is a class dao which declare all method communicate with the user_client_relationship collection in the database crm.
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
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

    @Override
    public boolean checkRelation(int idUser, int idClient) {
        Query<UserClientRelation> query = createQuery().field(USER_ID).equal(idUser).field(CLIENT_ID).equal(idClient);
        if (query.get() == null){
            return false;
        } else {
            return true;
        }
    }
}
