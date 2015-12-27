package miage.pds.prospect.dao;

import miage.pds.prospect.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Truong on 12/20/2015.
 */
public interface UserDAO {

    /**
     * get User with its login
     * @param login
     * @return
     */
    public User getUserByLogin(String login);

    /**
     * getListUser
     * @return
     */
    public List<User> getUsers();

}
