package fr.pds.isintheair.controller;

import fr.pds.isintheair.entity.Message;
import fr.pds.isintheair.enumeration.PeerType;

import javax.websocket.Session;

public class MessageController {
    public static void handleMessage(Message message, Session session) {
        switch (message.getMessageMeta().getMessageType()) {
            case REGISTER_PHONE:
                RegisterController.register(PeerType.PHONE, message.getRegister().getUserId(), session);
                break;
            case REGISTER_TABLET:
                RegisterController.register(PeerType.TABLET, message.getRegister().getUserId(), session);
                break;
            case CALL:
                CallController.call(session, message.getCall().getPhoneNumber());
                break;
            case CALL_END:
                CallController.endCall(session);
                break;
            case CALL_RECEIVED:
                CallController.notifyCallReceived(session, message.getCall().getPhoneNumber());
                break;
        }
    }
}
