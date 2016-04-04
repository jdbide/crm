package fr.pds.isintheair.crmtab.controller.message;

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
 */
public class PhoningCampaignController  {
    LinkedHashMap<Customer,List<Contact>> customerListHashMap;
    int currentCustomerposition;
    int currentContactPosition;
    PhoningCampaign phoningCampaign;
    CallPhoningCampaignFragment fragment;
    Customer currentCustomer;

    public PhoningCampaignController(HashMap<Customer, List<Contact>> customerListHashMap,
                                     PhoningCampaign phoningCampaign, CallPhoningCampaignFragment fragment) {
        this.customerListHashMap = new LinkedHashMap<>(customerListHashMap);
        this.phoningCampaign = phoningCampaign;
        this.fragment = fragment;
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
        fragment.initView(phoningCampaign,currentContact,currentCustomer);
        fragment.startCall();

    }


    public void UpdateCurrentCustomer() {
        currentCustomer = CustomerHelper.
                getCustomerByIndex(currentCustomerposition, customerListHashMap);
    }



    public void EndCall() {

        if(PhoningCampaignHelper.isLastContact(customerListHashMap,currentContactPosition,currentCustomer)) {
            if(CustomerHelper.getCustomerByIndex(customerListHashMap.size()-1,customerListHashMap) == currentCustomer) {
                endCampaign();

            } else {
                currentCustomerposition++;
                currentContactPosition = 0;
                BeginCampaign();
            }
        } else {
            currentContactPosition++;
            BeginCampaign();
        }
    }

    public void endCampaign() {
Log.d("endFragment","EndCampaign");
    }


}