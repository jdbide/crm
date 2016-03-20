package fr.pds.isintheair.crmtab.controller.message;

import fr.pds.isintheair.crmtab.model.dao.UserDAO;
import fr.pds.isintheair.crmtab.model.entity.CalendarMessage;
import fr.pds.isintheair.crmtab.model.entity.CallMessage;
import fr.pds.isintheair.crmtab.model.entity.DeviceType;
import fr.pds.isintheair.crmtab.model.entity.MessageInfo;
import fr.pds.isintheair.crmtab.model.entity.MessageType;
import fr.pds.isintheair.crmtab.model.entity.NotificationType;
import fr.pds.isintheair.crmtab.model.entity.SessionInfo;
import fr.pds.isintheair.crmtab.model.websocket.WebSocketConnectionHandlerSingleton;

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
