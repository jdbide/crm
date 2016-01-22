package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.MessageController;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.BusHandlerSingleton;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event.PhoneCallBegunEvent;
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
