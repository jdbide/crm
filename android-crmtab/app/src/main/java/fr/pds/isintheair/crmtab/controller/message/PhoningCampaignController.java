package fr.pds.isintheair.crmtab.controller.message;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import fr.pds.isintheair.crmtab.helper.CustomerHelper;
import fr.pds.isintheair.crmtab.model.dao.ContactCampaignDAO;
import fr.pds.isintheair.crmtab.model.dao.ContactDAO;
import fr.pds.isintheair.crmtab.model.entity.Contact;
import fr.pds.isintheair.crmtab.model.entity.ContactCampaign;
import fr.pds.isintheair.crmtab.model.entity.Customer;
import fr.pds.isintheair.crmtab.model.entity.PhoningCampaign;
import fr.pds.isintheair.crmtab.view.activity.MainActivity;
import fr.pds.isintheair.crmtab.view.fragment.CallPhoningCampaignFragment;

/**
 * Created by tlacouque on 03/04/2016.
 */
public class PhoningCampaignController {
    LinkedHashMap<Customer,List<Contact>> customerListHashMap;
    int currentCustomerposition;
    int currentContactPosition;
    PhoningCampaign phoningCampaign;
    MainActivity activity;
    Customer currentCustomer;

    public PhoningCampaignController(HashMap<Customer, List<Contact>> customerListHashMap, PhoningCampaign phoningCampaign, MainActivity activity) {
        this.customerListHashMap = new LinkedHashMap<>(customerListHashMap);
        this.phoningCampaign = phoningCampaign;
        this.activity = activity;
        currentCustomerposition = 0;
        currentContactPosition = 0;
    }

    public void BeginCampaign() {
        UpdateCurrentCustomer();
        BeginCall();

    }

    public void BeginCall() {
        Contact currentContact = customerListHashMap.get(currentCustomer)
                .get(currentContactPosition);
        ContactCampaign contactCampaign = ContactCampaignDAO
                .getContactCampaignFromIds(currentContact.getContactId(), phoningCampaign.getCampaignId());
        Bundle bundle = new Bundle();

        bundle.putParcelable(CallPhoningCampaignFragment.KEY_PHONING_CAMPAIGN,
                phoningCampaign);
        bundle.putParcelable(CallPhoningCampaignFragment.KEY_CUSTOMER,currentCustomer);
        bundle.putParcelable(CallPhoningCampaignFragment.KEY_CONTACT_CAMPAIGN, contactCampaign);
        bundle.putParcelable(CallPhoningCampaignFragment.KEY_CONTACT, currentContact);

    }


    public void UpdateCurrentCustomer() {
        currentCustomer = CustomerHelper.
                getCustomerByIndex(currentCustomerposition, customerListHashMap);


    }

}