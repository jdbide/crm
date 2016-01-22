package miage.pds.api.tlacouque.uc.admin.ref.customer.message;

import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.HealthCenter;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.Holding;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.PurchasingCentral;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Company;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Independant;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Specialty;
import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.Customer;

import java.util.List;

/**
 * Created by tlacouque on 17/12/2015.
 * DTO used between the current rest server, to Android crm tab.
 */
public class ResponseRestCustomer {

    private boolean isInserted;

    private List<Holding> holdings;

    private List<PurchasingCentral> purchasingCentrals;

    private List<Company> companies;

    private List<Specialty> specialties;

    private List<HealthCenter> healthCenters;

    private List<Independant> independants;

    private List<Customer> customers;



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

    public List<HealthCenter> getHealthCenters() {
        return healthCenters;
    }

    public void setHealthCenters(List<HealthCenter> healthCenters) {
        this.healthCenters = healthCenters;
    }

    public List<Independant> getIndependants() {
        return independants;
    }

    public void setIndependants(List<Independant> independants) {
        this.independants = independants;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
