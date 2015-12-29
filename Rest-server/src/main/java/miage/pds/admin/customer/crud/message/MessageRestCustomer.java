package miage.pds.admin.customer.crud.message;

import miage.pds.admin.customer.crud.createhc.entities.HealthCenter;
import miage.pds.admin.customer.crud.createindep.entities.Independant;

/**
 * Created by tlacouque on 17/12/2015.
 */
public class MessageRestCustomer {

    private int idUser;
    private HealthCenter healthCenter;
    private Independant independant;

    public MessageRestCustomer() {
    }

    public MessageRestCustomer(int idUser, HealthCenter healthEtablishment) {
        this.idUser = idUser;
        this.healthCenter = healthEtablishment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public HealthCenter getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(HealthCenter healthEtablishment) {
        this.healthCenter = healthEtablishment;
    }

    public Independant getIndependant() {
        return independant;
    }

    public void setIndependant(Independant independant) {
        this.independant = independant;
    }
}
