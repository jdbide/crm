package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.squareup.otto.Subscribe;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;
import fr.pds.isintheair.crmtab.common.model.database.entity.Contact_Table;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.MessageController;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.BusHandlerSingleton;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event.PhoneCallCutEvent;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event.PhoneCallEndedEvent;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event.PhoneCallFailedEvent;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event.PhoneCallHookedEvent;

public class CallActivity extends Activity {
    @Bind(R.id.phone_state_textview)
    TextView phoneStateTextview;

    @Bind(R.id.timer_textview)
    TextView timerTextview;

    private Contact currentContact;
    private String  currentPhoneNumber;
    private long    startCallTime;

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

        String phoneStateMessage = null;

        currentPhoneNumber = getIntent().getStringExtra("phoneNumber");
        currentContact = new Select().from(Contact.class).where(Contact_Table.phoneNumber.is(currentPhoneNumber)).querySingle();

        if (currentContact != null) {
            String fullContactName = currentContact.getFirstName() + " " + currentContact.getLastName();
            phoneStateMessage = "Appel de " + fullContactName + " en cours...";
        }

        else {
            phoneStateMessage = "Appel de " + currentPhoneNumber + " en cours...";
        }

        phoneStateTextview.setText(phoneStateMessage);
    }

    private void updateTimer() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                long millis  = System.currentTimeMillis() - startCallTime;
                int  seconds = (int) (millis / 1000);
                int  minutes = seconds / 60;
                seconds = seconds % 60;

                timerTextview.setText(String.format("%d:%02d", minutes, seconds));
            }
        });
    }

    @Subscribe
    public void onPhoneCallEndedEvent(PhoneCallEndedEvent phoneCallEndedEvent) {
        if (currentContact == null) {
            currentContact = new Contact();
            currentContact.setPhoneNumber(currentPhoneNumber);
        }

        BusHandlerSingleton.getInstance().getBus().post(new PhoneCallCutEvent(currentContact));
        finish();
    }

    @Subscribe
    public void onPhoneCallFailedEvent(PhoneCallFailedEvent phoneCallFailedEvent) {
        Toast.makeText(this, "Impossible de passer un appel : " + phoneCallFailedEvent.getFailReason(), Toast.LENGTH_LONG).show();
        finish();
    }

    @Subscribe
    public void onCallHooked(PhoneCallHookedEvent phoneCallHookedEvent) {
        phoneStateTextview.setText("Appel décroché");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startCallTime = System.currentTimeMillis();

                updateTimer();
            }
        }, 0, 1000);
    }
}
