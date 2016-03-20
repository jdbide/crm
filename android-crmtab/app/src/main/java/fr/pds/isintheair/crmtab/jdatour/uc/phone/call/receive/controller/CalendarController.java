package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller;

import fr.pds.isintheair.crmtab.common.model.database.dao.UserDAO;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.CalendarMessage;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.CallMessage;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.DeviceType;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.MessageInfo;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.MessageType;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.NotificationType;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.SessionInfo;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.websocket.WebSocketConnectionHandlerSingleton;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 03/19/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class CalendarController {
    public static void handleMessage(CalendarMessage message) {
    }

    public static void sendRegisterMessage() {
        Integer     userId      = UserDAO.getCurrentUser().getEmail().hashCode();
        MessageInfo messageInfo = new MessageInfo.Builder().addMessageType(MessageType.REGISTER).build();
        SessionInfo sessionInfo = new SessionInfo.Builder().addDeviceType(DeviceType.TABLET).addNotificationType(NotificationType.CALENDAR).addUserId(userId).build();
        CallMessage message     = new CallMessage.Builder().addMessageInfo(messageInfo).addSessionInfo(sessionInfo).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }
}
