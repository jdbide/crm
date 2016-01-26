package miage.pds.api.ctruong.uc.prospect.suggest.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

/**
 * The user model class
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
@Entity(value = "user")
public class User {

    @Id
    private int id;
    private String login;

    /**
     *
     */
    public User() {

    }

    /**
     *
     * @param id
     * @param login
     */
    public User(int id, String login) {
        this.id = id;
        this.login = login;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                ", id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
