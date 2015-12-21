package miage.pds.admin.customer.crud.message;

import miage.pds.admin.customer.crud.createhc.entities.HealthCenter;

/**
 * Created by tlacouque on 17/12/2015.
 */
public class MessageRestCustomer {

    private int idUser;
    private HealthCenter healthEtablishment;

    public MessageRestCustomer() {
    }

    public MessageRestCustomer(int idUser, HealthCenter healthEtablishment) {
        this.idUser = idUser;
        this.healthEtablishment = healthEtablishment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public HealthCenter getHealthCenter() {
        return healthEtablishment;
    }

    public void setHealthCenter(HealthCenter healthEtablishment) {
        this.healthEtablishment = healthEtablishment;
    }
}
