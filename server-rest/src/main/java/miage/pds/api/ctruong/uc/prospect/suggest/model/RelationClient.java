package miage.pds.api.ctruong.uc.prospect.suggest.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by Truong on 2/6/2016.
 */
@Entity("relation")
public class RelationClient {

    @Id
    private ObjectId id;

    @Property
    private long clientId;

    @Property
    private long prospectId;

    public RelationClient() {
    }

    public RelationClient(long clientId, long prospectId) {
        this.clientId = clientId;
        this.prospectId = prospectId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getProspectId() {
        return prospectId;
    }

    public void setProspectId(long prospectId) {
        this.prospectId = prospectId;
    }

    @Override
    public String toString() {
        return "RelationClient{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", prospectId=" + prospectId +
                '}';
    }
}
