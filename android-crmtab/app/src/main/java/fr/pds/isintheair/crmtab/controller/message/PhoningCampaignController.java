package fr.pds.isintheair.crmtab.controller.message;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.helper.CustomerHelper;
import fr.pds.isintheair.crmtab.helper.PhoningCampaignHelper;
import fr.pds.isintheair.crmtab.model.dao.ContactCampaignDAO;
import fr.pds.isintheair.crmtab.model.dao.ContactDAO;
import fr.pds.isintheair.crmtab.model.entity.Contact;
import fr.pds.isintheair.crmtab.model.entity.ContactCampaign;
import fr.pds.isintheair.crmtab.model.entity.Customer;
import fr.pds.isintheair.crmtab.model.entity.PhoningCampaign;
import fr.pds.isintheair.crmtab.view.activity.MainActivity;
import fr.pds.isintheair.crmtab.view.fragment.CallPhoningCampaignFragment;
import fr.pds.isintheair.crmtab.view.fragment.DetailPhoningCampaignFragment;

/**
 * Created by tlacouque on 03/04/2016.
 * Class used to controll a phoning campaign
 */
public class PhoningCampaignController  {
    LinkedHashMap<Customer,List<Contact>> customerListHashMap;
    int currentCustomerposition;
    int currentContactPosition;
    PhoningCampaign phoningCampaign;
    CallPhoningCampaignFragment fragment;
    Customer currentCustomer;
    ContactCampaign contactCampaign;


    public PhoningCampaignController(HashMap<Customer, List<Contact>> customerListHashMap,
                                     PhoningCampaign phoningCampaign, CallPhoningCampaignFragment fragment) {
        this.customerListHashMap = new LinkedHashMap<>(customerListHashMap);
        this.phoningCampaign = phoningCampaign;
        this.fragment = fragment;
        currentCustomerposition = 0;
        currentContactPosition = 0;
    }

    public void BeginCampaign() {
        phoningCampaign.setStatut(PhoningCampaign.STATE_BEGINED);
        BeginCall();

    }

    public void BeginCall() {
        UpdateCurrentCustomer();
        Contact currentContact = customerListHashMap.get(currentCustomer)
                .get(currentContactPosition);
         contactCampaign = ContactCampaignDAO
                .getContactCampaignFromIds(currentContact.getContactId(), phoningCampaign.getCampaignId());
        fragment.initView(phoningCampaign, currentContact, currentCustomer);
        fragment.startCall();

    }


    public void UpdateCurrentCustomer() {
        currentCustomer = CustomerHelper.
                getCustomerByIndex(currentCustomerposition, customerListHashMap);
    }



    public void EndCall() {
        // Test if the next contact is the last contact of the current customer
        if(PhoningCampaignHelper.isLastContact(customerListHashMap,currentContactPosition,currentCustomer)) {
            // Check if the customer is the last customer of the list of all customer to call
            if(CustomerHelper.getCustomerByIndex(customerListHashMap.size()-1,customerListHashMap) == currentCustomer) {
                endCampaign();

            } else {
                // Pass to the next customer and set the contact position to 0
                currentCustomerposition++;
                currentContactPosition = 0;
                BeginCall();
            }
        } else {
            // Called if there is an another contact for a customer, and start a new call
            currentContactPosition++;
            BeginCall();
        }
    }

    public void endCampaign() {
        phoningCampaign.setStatut(PhoningCampaign.STATE_ENDED);
        fragment.getFragmentManager().popBackStack("createPhoning", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void saveCurrentContactInfo(String info) {
        contactCampaign.setContactInfo(info);
        contactCampaign.setStatus(ContactCampaign.STATE_ENDED);
        contactCampaign.save();
    }


}