package pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.message;

import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.HealthCenter;

/**
 * Created by tlacouque on 17/12/2015.
 */
public class MessageRestCustomer {


    private int idUser;
    private HealthCenter healthCenter;

    public MessageRestCustomer(int idUser, HealthCenter healthCenter) {
        this.idUser = idUser;
        this.healthCenter = healthCenter;
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

    public void setHealthCenter(HealthCenter healthCenter) {
        this.healthCenter = healthCenter;
    }
}
