package miage.pds.admin.customer.crud.message;

import miage.pds.admin.customer.crud.createhc.entities.Holding;
import miage.pds.admin.customer.crud.createhc.entities.PurchasingCentral;

import java.util.List;

/**
 * Created by tlacouque on 17/12/2015.
 */
public class ResponseRestCustomer {

    private boolean isInserted;

    private List<Holding> holdings;

    private List<PurchasingCentral> purchasingCentrals;



    public boolean getIsInserted() {
        return isInserted;
    }

    public void setIsInserted(boolean isInserted) {
        this.isInserted = isInserted;
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
