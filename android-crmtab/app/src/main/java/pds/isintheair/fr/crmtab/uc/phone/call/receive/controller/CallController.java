package pds.isintheair.fr.crmtab.uc.phone.call.receive.controller;

import java.util.HashMap;

import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.bus.BusHandlerSingleton;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.bus.event.PhoneCallBegunEvent;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.bus.event.PhoneCallReceivedEvent;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.entity.Message;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.entity.MessageFactory;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.websocket.WebSocketConnectionHandlerSingleton;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.util.Constant;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.util.JSONHelper;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.util.enumeration.MessageType;

public class CallController {
    public static void call(String phoneNumber) {
        HashMap<String, String> callInformations = new HashMap<>();

        callInformations.put(Constant.MESSAGE_INFORMATIONS_PHONE_NUMBER, phoneNumber);

        WebSocketConnectionHandlerSingleton.getInstance()
                                           .sendMessage(JSONHelper.serialize(MessageFactory.buildMessage(
                                                                                     MessageType.CALL,
                                                                                     callInformations),
                                                                             Message.class));
    }

    public static void endCall() {
        WebSocketConnectionHandlerSingleton.getInstance()
                                           .sendMessage(JSONHelper.serialize(MessageFactory.buildMessage(
                                                   MessageType.CALL_END, null), Message.class));
    }

    public static void notifiyCallOk() {
        BusHandlerSingleton.getInstance().getBus().post(new PhoneCallBegunEvent());
    }

    public static void notifyCallReceived(String phoneNumber) {
        PhoneCallReceivedEvent phoneCallReceivedEvent = new PhoneCallReceivedEvent(phoneNumber);

        BusHandlerSingleton.getInstance().getBus().post(phoneCallReceivedEvent);
    }
}
