package miage.pds.prospect.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

/**
 * The user client relationship model class
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
@Entity("user_client_relationship")
public class UserClientRelation {

    @Id
    private ObjectId objectId;
    private int id;
    @Indexed
    private int idClient;
    @Indexed
    private int idUser;

    public UserClientRelation() {

    }

    /**
     *
     * Full constructor
     * @param id
     * @param idClient
     * @param idUser
     */
    public UserClientRelation(int id, int idClient, int idUser) {
        this.id = id;
        this.idClient = idClient;
        this.idUser = idUser;
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

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "UserClientRelation{" +
                "objectId=" + objectId +
                ", id=" + id +
                ", idClient=" + idClient +
                ", idUser=" + idUser +
                '}';
    }
}
