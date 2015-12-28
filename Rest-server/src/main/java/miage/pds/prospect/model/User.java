package miage.pds.prospect.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by Truong on 12/20/2015.
 * @version 1.1
 * @since 20/12/2015
 * @see User model
 */
@Entity("user")
public class User {

    @Id
    private ObjectId objectId;
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

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
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
                "objectId=" + objectId +
                ", id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}