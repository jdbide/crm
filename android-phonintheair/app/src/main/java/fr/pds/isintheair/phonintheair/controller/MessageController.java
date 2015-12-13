package fr.pds.isintheair.phonintheair.controller;

import fr.pds.isintheair.phonintheair.entity.Message;

public class MessageController {
    public static void handleMessage(Message message) {
        switch (message.getMessageMeta()
                       .getMessageType()) {
            case CALL:
                CallController.call(message.getCall().getPhoneNumber());
                break;
        }
    }
}
