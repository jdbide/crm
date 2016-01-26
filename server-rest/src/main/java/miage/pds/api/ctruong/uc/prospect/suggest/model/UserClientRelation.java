package miage.pds.api.ctruong.uc.prospect.suggest.model;

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
    @Indexed
    private int idClient;
    @Indexed
    private int idUser;

    public UserClientRelation() {

    }

    /**
     *
     * Full constructor
     * @param idClient
     * @param idUser
     */
    public UserClientRelation(int idClient, int idUser) {
        this.idClient = idClient;
        this.idUser = idUser;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
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
                ", idClient=" + idClient +
                ", idUser=" + idUser +
                '}';
    }
}
