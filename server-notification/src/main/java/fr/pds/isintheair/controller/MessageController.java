package fr.pds.isintheair.controller;

import fr.pds.isintheair.entity.Message;

import javax.websocket.Session;

public class MessageController {
    public static void handleMessage(Message message, Session session) {
        switch (message.getMessageMeta().getMessageType()) {
            case REGISTER_TABLET:
                RegisterController.registerTablet(message.getRegister().getUserId(), session);
                break;
            case CALL:
                break;
        }
    }
}
