package fr.pds.isintheair.crmtab.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.controller.bus.BusHandlerSingleton;
import fr.pds.isintheair.crmtab.controller.bus.event.PhoneCallEndedEvent;
import fr.pds.isintheair.crmtab.controller.bus.event.PhoneCallFailedEvent;
import fr.pds.isintheair.crmtab.controller.bus.event.PhoneCallHookedEvent;
import fr.pds.isintheair.crmtab.controller.message.CallMessageController;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Constants;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.entity.CallEndedEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.enums.CallType;
import fr.pds.isintheair.crmtab.model.entity.Contact;
import fr.pds.isintheair.crmtab.model.entity.ContactCampaign;
import fr.pds.isintheair.crmtab.model.entity.Customer;
import fr.pds.isintheair.crmtab.model.entity.PhoningCampaign;
import fr.pds.isintheair.crmtab.view.activity.MainActivity;

/**
 * Created by tlacouque on 03/04/2016.
 */
public class CallPhoningCampaignFragment extends Fragment {

    public static String KEY_CONTACT_CAMPAIGN = "CONTACT_CAMPAIGN";
    public static String KEY_CONTACT = "CONTACT";
    public static String KEY_CUSTOMER = "CUSTOMER";
    public static String KEY_PHONING_CAMPAIGN = "KEY_PHONING_CAMPAIGN";

    Customer customer;
    Contact contact;
    ContactCampaign contactCampaign;
    PhoningCampaign phoningCampaign;
    boolean callBegin;


    @Bind(R.id.create_phoning_campaign_fragment_phone)
    Button callButton;

    @Bind(R.id.call_phoning_campaign_fragment_title)
    TextView titre;

    @Bind(R.id.call_phoning_campaign_fragment_objective)
    TextView objective;

    @Bind(R.id.call_phoning_campaign_fragment_contact_name_firstname)
    TextView contactNameFname;

    @Bind(R.id.call_phoning_campaign_fragment_campaign_type)
    TextView type;

    @Bind(R.id.call_phoning_campaign_fragment_contact_job)
    TextView  contactJob;

    @Bind(R.id.call_phoning_campaign_fragment_customer_name)
    TextView customerName;

    @Bind(R.id.call_phoning_campaign_fragment_commentary)
    EditText commentary;


    /**
     * Can be called when a new CallPhoningCampaignFragment is needed
     *
     * @return CallPhoningCampaignFragment
     */
    public static CallPhoningCampaignFragment newInstance() {
        CallPhoningCampaignFragment fragment = new CallPhoningCampaignFragment();
        Bundle args     = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactCampaign =
                (ContactCampaign) this.getArguments().getSerializable(CallPhoningCampaignFragment.KEY_CONTACT_CAMPAIGN);
        contact = this.getArguments().getParcelable(CallPhoningCampaignFragment.KEY_CONTACT);
        customer = this.getArguments().getParcelable(CallPhoningCampaignFragment.KEY_CUSTOMER);
        phoningCampaign = this.getArguments().getParcelable(CallPhoningCampaignFragment.KEY_PHONING_CAMPAIGN);
        callBegin = false;
        BusHandlerSingleton.getInstance().getBus().register(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_call_phoning_campaign, container, false);
        ButterKnife.bind(this, v);
        initView();
        startCall();
        return v;
    }


    public void initView() {
        titre.setText(phoningCampaign.getCampaignTheme());
         objective.setText(phoningCampaign.getCampaignObjectives());
         contactNameFname.setText(contact.contactName+" "+contact.contactFname);
         type.setText(phoningCampaign.getCampaignType());
          contactJob.setText(contact.contactJob);
         customerName.setText(customer.getName());
    }


    public void startCall() {
        CallMessageController.sendCallMessage(contact.contactTel);
    }


    @Subscribe
    public void onPhoneCallEndedEvent(PhoneCallEndedEvent phoneCallEndedEvent) {
        Log.d("callFragment","callEnded");
    }

    @Subscribe
    public void onPhoneCallFailedEvent(PhoneCallFailedEvent phoneCallFailedEvent) {
        Log.d("callFragment","failed");
    }

    @Subscribe
    public void onCallHooked(PhoneCallHookedEvent phoneCallHookedEvent) {
        Log.d("callFragment","Hoocked");
}

    @OnClick(R.id.create_phoning_campaign_fragment_phone)
    public void onPhoneClick() {
        CallMessageController.sendEndCallMessage();
    }




}
