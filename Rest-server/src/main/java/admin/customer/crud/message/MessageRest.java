package admin.customer.crud.message;

import admin.customer.crud.createhc.entities.HealthCenter;

/**
 * Created by tlacouque on 17/12/2015.
 */
public class MessageRest {

    private int idUser;
    private HealthCenter healthEtablishment;

    public MessageRest(int idUser, HealthCenter healthEtablishment) {
        this.idUser = idUser;
        this.healthEtablishment = healthEtablishment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public HealthCenter getHealthEtablishment() {
        return healthEtablishment;
    }

    public void setHealthEtablishment(HealthCenter healthEtablishment) {
        this.healthEtablishment = healthEtablishment;
    }
}
