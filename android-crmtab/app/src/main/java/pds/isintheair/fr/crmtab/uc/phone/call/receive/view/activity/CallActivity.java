package pds.isintheair.fr.crmtab.uc.phone.call.receive.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pds.isintheair.fr.crmtab.R;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.controller.CallController;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.bus.BusHandlerSingleton;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.bus.event.PhoneCallBegunEvent;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.bus.event.PhoneCallEndedEvent;

public class CallActivity extends Activity {
    @Bind(R.id.phone_state_textview)
    TextView phoneStateTextview;

    @OnClick(R.id.phone_imageview)
    public void onPhoneClick() {
        CallController.endCall();

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ButterKnife.bind(this);
        BusHandlerSingleton.getInstance().getBus().register(this);

        //TODO Remove hard coded string
        phoneStateTextview.setText("Appel ...");
    }

    @Subscribe
    public void onPhoneCallBegunEvent(PhoneCallBegunEvent phoneCallBegunEvent) {
        //TODO Remove hard coded string
        phoneStateTextview.setText("Appel en cours ...");
    }

    @Subscribe
    public void onPhoneCallEndedEvent(PhoneCallEndedEvent phoneCallEndedEvent) {
        finish();
    }
}
