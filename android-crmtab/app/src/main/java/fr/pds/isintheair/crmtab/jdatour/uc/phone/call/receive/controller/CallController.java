package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller;

import android.content.Intent;

import fr.pds.isintheair.crmtab.common.CrmTabApplication;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.BusHandlerSingleton;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event.PhoneCallEndedEvent;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.view.CallActivity;

public class CallController {
    public static void call(String phoneNumber) {
        MessageController.sendCallMessage(phoneNumber);
    }

    public static void endCall() {
        BusHandlerSingleton.getInstance().getBus().post(new PhoneCallEndedEvent());
    }

    public static void notifyCallReceived(String phoneNumber) {
        Intent intent = new Intent();

        intent.setClass(CrmTabApplication.context, CallActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        CrmTabApplication.context.startActivity(intent);
    }
}
