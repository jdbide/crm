package fr.pds.isintheair.phonintheair.controller;

import java.util.List;

import fr.pds.isintheair.phonintheair.PhonintheairApp;
import fr.pds.isintheair.phonintheair.model.calendar.CalendarProvider;
import fr.pds.isintheair.phonintheair.model.entity.CalendarMessage;
import fr.pds.isintheair.phonintheair.model.entity.Event;
import fr.pds.isintheair.phonintheair.model.entity.MessageMeta;
import fr.pds.isintheair.phonintheair.model.enumeration.MessageType;
import fr.pds.isintheair.phonintheair.model.websocket.WebSocketConnectionHandlerSingleton;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 03/19/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class CalendarController {
    public static void handleMessage(CalendarMessage message) {
        switch (message.getMessageMeta().getMessageType()) {

            default:
        }
    }

    public static void sendFullSyncMessage() {
        List<Event>     events          = new CalendarProvider(PhonintheairApp.context).getEvents(0l);
        MessageMeta     messageMeta     = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALENDAR_FULL_SYNC).build();
        CalendarMessage calendarMessage = new CalendarMessage.Builder().addMessageMeta(messageMeta).addEvents(events).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(calendarMessage);
    }
}
