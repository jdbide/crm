package miage.pds.prospect.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

/**
 * Created by Truong on 12/20/2015.
 * @version 1.1
 * @since 20/12/2015
 * @see Sales model
 */
@Entity("sales")
public class Sales{

    @Id
    private ObjectId objectId;
    private int id;
    private int idClient;
    private int idSeller;
    private String date;
    private double value;

    public Sales() {

    }

    /**
     * Full constructor
     * @param id
     * @param idClient
     * @param idSeller
     * @param date
     * @param value
     */
    public Sales(int id, int idClient, int idSeller, String date, double value) {
        this.id = id;
        this.idClient = idClient;
        this.idSeller = idSeller;
        this.date = date;
        this.value = value;
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

    public int getIdProspect() {
        return idClient;
    }

    public void setIdProspect(int idProspect) {
        this.idClient = idProspect;
    }

    public int getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "objectId=" + objectId +
                ", id=" + id +
                ", idClient=" + idClient +
                ", idSeller=" + idSeller +
                ", date='" + date + '\'' +
                ", value=" + value +
                '}';
    }
}
