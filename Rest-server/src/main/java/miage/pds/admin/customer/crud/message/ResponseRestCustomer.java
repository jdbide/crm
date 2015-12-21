package miage.pds.admin.customer.crud.message;

import miage.pds.admin.customer.crud.createhc.entities.Holding;
import miage.pds.admin.customer.crud.createhc.entities.PurchasingCentral;

import java.util.List;

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
