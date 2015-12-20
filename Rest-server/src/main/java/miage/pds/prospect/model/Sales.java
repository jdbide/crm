package miage.pds.prospect.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Truong on 12/20/2015.
 * @version 1.1
 * @since 20/12/2015
 * @see Sales model
 */

@Document(collection = "sale")
public class Sales {

    @Id
    private Integer id;

    @Indexed(name = "idProspect", unique = true)
    private Prospect idProspect;

    private double salesValue;


    @PersistenceConstructor
    public Sales(Prospect idProspect, double salesValue) {
        setIdProspect(idProspect);
        setSalesValue(salesValue);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Prospect getIdProspect() {
        return idProspect;
    }

    public void setIdProspect(Prospect idProspect) {
        this.idProspect = idProspect;
    }

    public double getSalesValue() {
        return salesValue;
    }

    public void setSalesValue(double salesValue) {
        this.salesValue = salesValue;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", idProspect=" + idProspect +
                ", salesValue=" + salesValue +
                '}';
    }
}
