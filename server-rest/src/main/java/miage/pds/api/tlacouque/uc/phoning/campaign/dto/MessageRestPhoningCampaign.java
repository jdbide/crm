package miage.pds.api.tlacouque.uc.phoning.campaign.dto;

import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.HealthCenter;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Independant;
import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlacouque on 27/03/2016.
 */
public class MessageRestPhoningCampaign {
    private ArrayList<String> customersId;

    public ArrayList<String> getCustomersId() {
        return customersId;
    }

    public void setCustomersId(ArrayList<String> customersId) {
        this.customersId = customersId;
    }
}
