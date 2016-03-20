package fr.pds.isintheair.notifier.controller;

import fr.pds.isintheair.notifier.entity.CalendarMessage;

import javax.websocket.Session;

public class CalendarMessageController {
    public static void handleMessage (CalendarMessage calendarMessage, Session session) {
        switch (calendarMessage.getMessageInfo().getMessageType()) {
            case REGISTER:
                PeerHandlerSingleton.getInstance().addPeer(session, calendarMessage.getSessionInfo());
                break;
        }
    }
}
