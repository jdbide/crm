package pds.isintheair.fr.crm_tab.admin.referentiel.client.message;

import java.util.List;

import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.Holding;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.PurchasingCentral;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Company;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Specialty;

/**
 * Created by tlacouque on 17/12/2015.
 */
public class ResponseRestCustomer {

    private boolean isInserted;

    private List<Holding> holdings;

    private List<PurchasingCentral> purchasingCentrals;

    private List<Specialty> specialties;

    private List<Company> companies;



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


    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
