package miage.pds.prospect.dao;

import miage.pds.prospect.model.UserClientRelation;

import java.util.List;

/**
 * Created by Truong on 12/22/2015.
 */
public interface UserClientRelationDAO {

    public List<UserClientRelation> getClientByUserId(int idUser);

    public List<UserClientRelation> getUserByClientId(int idClient);
}
