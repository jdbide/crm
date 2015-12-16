package pds.isintheair.fr.crm_tab.uc.phone.call.receive.controller;

import pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.entity.Message;

public class MessageController {
    public static void handleMessage(Message message) {
        switch (message.getMessageMeta().getMessageType()) {
            case CALL_OK:
                CallController.call(message.getCall().getPhoneNumber());
                break;
        }
    }
}
