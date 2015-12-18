package pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities;

/**
 * Created by tlacouque on 13/12/2015.
 */
public class HealthEtablishmentResponse {
    private Boolean status;

    public HealthEtablishmentResponse(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
