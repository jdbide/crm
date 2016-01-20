package pds.isintheair.fr.crmtab.uc.phone.call.receive.controller;

import android.content.Intent;

import java.util.HashMap;

import pds.isintheair.fr.crmtab.CrmTabApplication;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.bus.BusHandlerSingleton;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.bus.event.PhoneCallBegunEvent;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.bus.event.PhoneCallEndedEvent;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.util.Constant;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.view.activity.CallActivity;

public class CallController {
    public static void call(String phoneNumber) {
        HashMap<String, String> callInformations = new HashMap<>();

        callInformations.put(Constant.MESSAGE_INFORMATIONS_PHONE_NUMBER, phoneNumber);

        MessageController.sendCallMessage("0610772364");
    }

    public static void endCall() {
        BusHandlerSingleton.getInstance().getBus().post(new PhoneCallEndedEvent());
    }

    public static void notifiyCallOk() {
        BusHandlerSingleton.getInstance().getBus().post(new PhoneCallBegunEvent());
    }

    public static void notifyCallReceived(String phoneNumber) {
        Intent intent = new Intent();

        intent.setClass(CrmTabApplication.context, CallActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        CrmTabApplication.context.startActivity(intent);

        /* PhoneCallReceivedEvent phoneCallReceivedEvent = new PhoneCallReceivedEvent(phoneNumber);

        BusHandlerSingleton.getInstance().getBus().post(phoneCallReceivedEvent);*/
    }
}
