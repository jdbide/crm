package miage.pds.api.ctruong.uc.prospect.suggest.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.util.Date;

/**
 * The sales model class
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
@Entity("sales")
public class Sales{

    @Id
    private ObjectId objectId;
    @Indexed
    private int idClient;
    @Indexed
    private int idSeller;
    private double value;
    private Date date;

    public Sales() {

    }

    /**
     * Full constructor
     * @param idClient
     * @param idSeller
     * @param date
     * @param value
     */
    public Sales(int idClient, int idSeller, Date date, double value) {
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

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
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
        return "Sales{" +
                ", idClient=" + idClient +
                ", idSeller=" + idSeller +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
