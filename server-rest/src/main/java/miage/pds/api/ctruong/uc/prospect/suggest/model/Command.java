package miage.pds.api.ctruong.uc.prospect.suggest.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.Date;

/**
 * Created by Truong on 2/6/2016.
 */
@Entity("command")
public class Command {

    @Id
    private ObjectId id;

    @Property
    private ObjectId prospectId;

    @Property
    private String person;

    @Property
    private long value;

    @Property
    private Date date;

    public Command() {
    }

    public Command(ObjectId prospectId, String person, long value, Date date) {
        this.prospectId = prospectId;
        this.person = person;
        this.value = value;
        this.date = date;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getProspectName() {
        return prospectId;
    }

    public void setProspectName(ObjectId prospectId) {
        this.prospectId = prospectId;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", prospectId='" + prospectId + '\'' +
                ", person='" + person + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
