package pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.message;

import java.util.List;

import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.Holding;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.PurchasingCentral;

/**
 * Created by tlacouque on 17/12/2015.
 */
public class ResponseRestCustomer {

    private int idClient;

    private List<Holding> holdings;

    private List<PurchasingCentral> purchasingCentrals;



    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idUser) {
        this.idClient = idUser;
    }

    public List<Holding> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<Holding> holdings) {
        this.holdings = holdings;
    }

    public List<PurchasingCentral> getPurchasingCentrals() {
        return purchasingCentrals;
    }

    public void setPurchasingCentrals(List<PurchasingCentral> purchasingCentrals) {
        this.purchasingCentrals = purchasingCentrals;
    }
}
