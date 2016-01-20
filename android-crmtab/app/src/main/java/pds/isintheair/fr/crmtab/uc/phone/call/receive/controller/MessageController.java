package pds.isintheair.fr.crmtab.uc.phone.call.receive.controller;

import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.entity.Call;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.entity.Message;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.entity.MessageMeta;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.entity.Register;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.websocket.WebSocketConnectionHandlerSingleton;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.util.enumeration.DeviceType;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.util.enumeration.MessageType;

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

        Register register = new Register(42);

        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addRegister(
                register).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }
}
