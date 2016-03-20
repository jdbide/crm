package fr.pds.isintheair.phonintheair.controller;

import java.util.List;

import fr.pds.isintheair.phonintheair.PhonintheairApp;
import fr.pds.isintheair.phonintheair.helper.SharedPreferencesHelper;
import fr.pds.isintheair.phonintheair.model.entity.CalendarMessage;
import fr.pds.isintheair.phonintheair.model.entity.CallMessage;
import fr.pds.isintheair.phonintheair.model.entity.DeviceType;
import fr.pds.isintheair.phonintheair.model.entity.Event;
import fr.pds.isintheair.phonintheair.model.entity.MessageInfo;
import fr.pds.isintheair.phonintheair.model.entity.MessageType;
import fr.pds.isintheair.phonintheair.model.entity.NotificationType;
import fr.pds.isintheair.phonintheair.model.entity.SessionInfo;
import fr.pds.isintheair.phonintheair.model.provider.CalendarProvider;
import fr.pds.isintheair.phonintheair.model.websocket.WebSocketConnectionHandlerSingleton;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 03/19/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class CalendarController {
    public static void handleMessage(CalendarMessage message) {
    }

    public static void sendFullSyncMessage() {
        List<Event>     events          = new CalendarProvider(PhonintheairApp.context).getEvents(1l);
        MessageInfo     messageInfo     = new MessageInfo.Builder().addMessageType(MessageType.CALENDAR_FULL_SYNC).build();
        CalendarMessage calendarMessage = new CalendarMessage.Builder().addMessageInfo(messageInfo).addEvents(events).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(calendarMessage);
    }

    public static void sendRegisterMessage() {
        Integer     userId      = SharedPreferencesHelper.readInteger("userId", 0);
        MessageInfo messageInfo = new MessageInfo.Builder().addMessageType(MessageType.REGISTER).build();
        SessionInfo sessionInfo = new SessionInfo.Builder().addDeviceType(DeviceType.PHONE).addNotificationType(NotificationType.CALENDAR).addUserId(userId).build();
        CallMessage message     = new CallMessage.Builder().addMessageInfo(messageInfo).addSessionInfo(sessionInfo).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }
}
