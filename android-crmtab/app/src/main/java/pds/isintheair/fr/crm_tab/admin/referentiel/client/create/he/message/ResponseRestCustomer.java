package pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.message;

import java.util.List;

import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.Holding;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.PurchasingCentral;

/**
 * Created by tlacouque on 17/12/2015.
 */
public class ResponseRestCustomer {

    private int idUser;

    private List<Holding> holdings;

    private List<PurchasingCentral> purchasingCentrals;



    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
