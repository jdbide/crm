package fr.pds.isintheair.crmtab.model.entity;

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
