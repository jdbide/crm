package fr.pds.isintheair.phonintheair.controller;

import fr.pds.isintheair.phonintheair.model.entity.Call;
import fr.pds.isintheair.phonintheair.model.entity.Message;
import fr.pds.isintheair.phonintheair.model.entity.MessageMeta;
import fr.pds.isintheair.phonintheair.model.entity.Register;
import fr.pds.isintheair.phonintheair.model.enumeration.DeviceType;
import fr.pds.isintheair.phonintheair.model.enumeration.MessageType;
import fr.pds.isintheair.phonintheair.model.websocket.WebSocketConnectionHandlerSingleton;

public class MessageController {
    public static void handleMessage(Message message) {
        switch (message.getMessageMeta()
                       .getMessageType()) {
            case CALL:
                CallController.call(message.getCall().getPhoneNumber());
                break;
            case CALL_END:
                CallController.endCall();
        }
    }

    public static void sendCallReceivedMessage(String phoneNumber) {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL_RECEIVED)
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
        message.setDeviceType(DeviceType.PHONE);

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }

    public static void sendRegisterMessage() {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_PHONE)
                                                                      .build();

        Register register = new Register(42);

        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addRegister(
                register).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }
}
