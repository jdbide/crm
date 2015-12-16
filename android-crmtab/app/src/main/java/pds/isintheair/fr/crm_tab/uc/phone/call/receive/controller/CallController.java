package pds.isintheair.fr.crm_tab.uc.phone.call.receive.controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import pds.isintheair.fr.crm_tab.CrmTabApplication;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.bus.BusHandlerSingleton;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.bus.event.PhoneCallBegunEvent;

public class CallController {
    public static void call(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        Context applicationContext = CrmTabApplication.context;

        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (ActivityCompat.checkSelfPermission(applicationContext,
                                               Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            applicationContext.startActivity(callIntent);
        }
    }

    public static void notifiyCallOk() {
        BusHandlerSingleton.getInstance().getBus().post(new PhoneCallBegunEvent());
    }
}
