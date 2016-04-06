package fr.pds.isintheair.crmtab.controller.message;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

    /**
     * Start the new campaign
     */
    public void BeginCampaign() {
        phoningCampaign.setStatut(PhoningCampaign.STATE_BEGINED);
        phoningCampaign.save();
        BeginCall();

    }

    /**
     * Start a new call
     */
    public void BeginCall() {
        UpdateCurrentCustomer();
        Contact currentContact = customerListHashMap.get(currentCustomer)
                .get(currentContactPosition);
         contactCampaign = ContactCampaignDAO
                .getContactCampaignFromIds(currentContact.getContactId(), phoningCampaign.getCampaignId());
        fragment.initView(phoningCampaign, currentContact, currentCustomer);
        fragment.startCall();

    }

    /**
     * Update the current customer if it is needed.
     */
    public void UpdateCurrentCustomer() {
        currentCustomer = CustomerHelper.
                getCustomerByIndex(currentCustomerposition, customerListHashMap);
    }


    /**
     * Called when the user click on the button "next call". Test if the next contact is the last of the current customer.
     * If it s false, it pass to the next contact. If t s true, it check if the customer is the last customer to call.
     * If it s true it call endCampaign(). if it s false it pass to the next customer
     */
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

    /**
     * Called when there is no contact left to call, it end the campaign
     */
    public void endCampaign() {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        phoningCampaign.setStatut(PhoningCampaign.STATE_ENDED);
        phoningCampaign.setEndDate(currentDateTimeString);
        phoningCampaign.save();
        fragment.getFragmentManager().popBackStack("detailHc", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Save commentary written by the commercial pass by the CallPhoningCampaignFragment
     * @param info
     */
    public void saveCurrentContactInfo(String info) {
        contactCampaign.setContactInfo(info);
        contactCampaign.setStatus(ContactCampaign.STATE_ENDED);
        contactCampaign.save();
    }


}