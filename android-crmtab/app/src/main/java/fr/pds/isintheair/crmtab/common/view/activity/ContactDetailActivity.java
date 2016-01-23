package fr.pds.isintheair.crmtab.common.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.CallController;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.view.CallActivity;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/23/2016         *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class ContactDetailActivity extends Activity {
    Contact currentContact;

    @Bind(R.id.call_information_textview)
    TextView callInformationTextview;

    @OnClick(R.id.sms_imageview)
    public void onSmsClick() {
        //TODO Implement click event
    }

    @OnClick(R.id.phone_imageview)
    public void onPhoneClick() {
        if (currentContact != null) {
            CallController.call(currentContact.getPhoneNumber());
            startActivity(new Intent(this, CallActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        ButterKnife.bind(this);

        currentContact = savedInstanceState.getParcelable("contact");

        if (currentContact != null) {
            String callInformations = currentContact.getFirstName() + " " + currentContact.getLastName() + " - " + currentContact.getPhoneNumber();

            callInformationTextview.setText(callInformations);
        }
    }

}
