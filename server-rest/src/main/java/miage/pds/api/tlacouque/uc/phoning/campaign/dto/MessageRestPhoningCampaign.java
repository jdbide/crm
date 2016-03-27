package miage.pds.api.tlacouque.uc.phoning.campaign.dto;

import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.HealthCenter;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Independant;
import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.Customer;

import java.util.List;

/**
 * Created by tlacouque on 27/03/2016.
 */
public class MessageRestPhoningCampaign {
    private List<Customer> customers;


    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
