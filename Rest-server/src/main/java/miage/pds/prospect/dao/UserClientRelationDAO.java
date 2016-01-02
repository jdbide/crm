package miage.pds.prospect.dao;

import miage.pds.prospect.model.UserClientRelation;

import java.util.List;

/**
 * This is a class dao which declare all method communicate with the user_client_relationship collection in the database crm.
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public interface UserClientRelationDAO {

    public List<UserClientRelation> getClientByUserId(int idUser);

    public List<UserClientRelation> getUserByClientId(int idClient);

    public long countRelationshipByClientID(int idClient);

    public boolean checkRelation(int idUser, int idClient);
}
