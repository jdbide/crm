package miage.pds.admin.customer.crud.message;

import miage.pds.admin.customer.crud.createhc.entities.Holding;
import miage.pds.admin.customer.crud.createhc.entities.PurchasingCentral;
import miage.pds.admin.customer.crud.createindep.entities.Company;
import miage.pds.admin.customer.crud.createindep.entities.Specialty;

import java.util.List;

/**
 * Created by tlacouque on 17/12/2015.
 */
public class ResponseRestCustomer {

    private boolean isInserted;

    private List<Holding> holdings;

    private List<PurchasingCentral> purchasingCentrals;

    private List<Company> companies;

    private List<Specialty> specialties;



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

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }
}
