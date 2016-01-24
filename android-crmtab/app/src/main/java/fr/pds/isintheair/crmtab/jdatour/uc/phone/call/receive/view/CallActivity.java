package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;
//import fr.pds.isintheair.crmtab.common.model.database.entity.Contact_Table;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.MessageController;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.BusHandlerSingleton;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event.PhoneCallEndedEvent;

public class CallActivity extends Activity {
    @Bind(R.id.phone_state_textview)
    TextView phoneStateTextview;

    @OnClick(R.id.phone_imageview)
    public void onPhoneClick() {
        MessageController.sendEndCallMessage();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ButterKnife.bind(this);
        BusHandlerSingleton.getInstance().getBus().register(this);
//
//        String  phoneNumber                  = getIntent().getStringExtra("phoneNumber");
//        Contact associatedPhoneNumberContact = new Select().from(Contact.class).where(Contact_Table.phoneNumber.is(phoneNumber)).querySingle();
//        String  phoneStateMessage            = null;
//
//        if (associatedPhoneNumberContact != null) {
//            String fullContactName = associatedPhoneNumberContact.getFirstName() + " " + associatedPhoneNumberContact.getLastName();
//            phoneStateMessage = "Appel de " + fullContactName + " en cours...";
//        }
//
//        else {
//            phoneStateMessage = "Appel de " + phoneNumber + " en cours...";
//        }
//
//        phoneStateTextview.setText(phoneStateMessage);
    }

    @Subscribe
    public void onPhoneCallEndedEvent(PhoneCallEndedEvent phoneCallEndedEvent) {
        finish();
    }
}
