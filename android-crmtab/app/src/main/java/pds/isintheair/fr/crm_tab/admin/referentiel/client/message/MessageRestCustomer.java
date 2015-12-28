package pds.isintheair.fr.crm_tab.admin.referentiel.client.message;

import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.HealthCenter;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Independant;

/**
 * Created by tlacouque on 17/12/2015.
 */
public class MessageRestCustomer {

    private int idUser;
    private HealthCenter healthCenter;
    private Independant independant;

    public MessageRestCustomer(int idUser, HealthCenter healthCenter) {
        this.idUser = idUser;
        this.healthCenter = healthCenter;
    }

    public MessageRestCustomer(int idUser, Independant independant) {
        this.idUser = idUser;
        this.independant = independant;
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

    public Independant getIndependant() {
        return independant;
    }

    public void setIndependant(Independant independant) {
        this.independant = independant;
    }
}
