package pds.isintheair.fr.crmtab.uc.phone.call.receive.controller;

import android.content.Intent;
import android.util.Log;

import com.squareup.otto.Subscribe;

import pds.isintheair.fr.crmtab.CrmTabApplication;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.bus.event.PhoneCallReceivedEvent;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.view.activity.CallActivity;

public class ActivityController {
    @Subscribe
    public void onPhoneCallReceveidEvent(PhoneCallReceivedEvent phoneCallReceivedEvent) {
        Log.d("", "PHONE CALL RECEIVED");
        Intent intent = new Intent();

        intent.setClass(CrmTabApplication.context, CallActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        CrmTabApplication.context.startActivity(intent);
    }
}
