package pds.isintheair.fr.crm_tab.uc.phone.call.receive.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.bus.BusHandlerSingleton;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.bus.event.PhoneCallBegunEvent;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.bus.event.PhoneCallEndedEvent;

public class CallActivity extends Activity {
    @Bind(R.id.phone_state_textview)
    TextView phoneStateTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ButterKnife.bind(this);
        BusHandlerSingleton.getInstance().getBus().register(this);

        phoneStateTextview.setText("Appel ...");
    }

    @Subscribe
    public void onPhoneCallBegunEvent(PhoneCallBegunEvent phoneCallBegunEvent) {
        phoneStateTextview.setText("Appel en cours ...");
    }

    @Subscribe
    public void onPhoneCallEndedEvent(PhoneCallEndedEvent phoneCallEndedEvent) {
        finish();
    }
}
