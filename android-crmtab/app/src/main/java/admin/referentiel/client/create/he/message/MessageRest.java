package admin.referentiel.client.create.he.message;

import admin.referentiel.client.create.he.entities.HealthEtablishment;

/**
 * Created by tlacouque on 17/12/2015.
 */
public class MessageRest {


    private int idUser;
    private HealthEtablishment healthEtablishment;

    public MessageRest(int idUser, HealthEtablishment healthEtablishment) {
        this.idUser = idUser;
        this.healthEtablishment = healthEtablishment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public HealthEtablishment getHealthEtablishment() {
        return healthEtablishment;
    }

    public void setHealthEtablishment(HealthEtablishment healthEtablishment) {
        this.healthEtablishment = healthEtablishment;
    }
}
