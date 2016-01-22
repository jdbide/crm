package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.User;

import java.util.List;

/**
 * This is a class dao which declare all method communicate with the user collection in the database crm.
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public interface UserDAO {

    /**
     * getListUser
     * @return
     */
    public List<User> getAllUsers();


}
