package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller;

import fr.pds.isintheair.crmtab.common.model.database.dao.UserDAO;
import fr.pds.isintheair.crmtab.common.model.database.entity.User;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.Call;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.Message;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.MessageMeta;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.Register;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.enumeration.DeviceType;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.enumeration.MessageType;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.websocket.WebSocketConnectionHandlerSingleton;

public class MessageController {
    public static void handleMessage(Message message) {
        switch (message.getMessageMeta().getMessageType()) {
            case CALL_END:
                CallController.endCall();
                break;
            case CALL_OK:
                CallController.call(message.getCall().getPhoneNumber());
                break;
            case CALL_RECEIVED:
                CallController.notifyCallReceived(message.getCall().getPhoneNumber());
                break;
        }
    }

    public static void sendCallMessage(String phoneNumber) {
        MessageType messageType = MessageType.CALL;

        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(messageType)
                                                                      .build();

        Call call = new Call(phoneNumber);

        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta)
                                                      .addCall(call)
                                                      .build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }

    public static void sendEndCallMessage() {
        MessageType messageType = MessageType.CALL_END;

        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(messageType)
                                                                      .build();

        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta)
                                                      .build();

        message.setDeviceType(DeviceType.TABLET);

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }

    public static void sendRegisterMessage() {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_TABLET)
                                                                      .build();

        User     currentUser = UserDAO.getCurrentUser();
        Register register    = new Register(currentUser.getEmail().hashCode());

        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addRegister(
                register).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }
}
