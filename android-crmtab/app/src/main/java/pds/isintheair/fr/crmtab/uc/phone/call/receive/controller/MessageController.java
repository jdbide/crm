package pds.isintheair.fr.crmtab.uc.phone.call.receive.controller;

import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.entity.Message;

public class MessageController {
    public static void handleMessage(Message message) {
        switch (message.getMessageMeta().getMessageType()) {
            case CALL_OK:
                CallController.call(message.getCall().getPhoneNumber());
                break;
            case CALL_RECEIVED:
                CallController.notifyCallReceived(message.getCall().getPhoneNumber());
                break;
        }
    }
}
