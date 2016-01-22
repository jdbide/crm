package fr.pds.isintheair.phonintheair.model.entity;

import fr.pds.isintheair.phonintheair.model.enumeration.MessageType;

public class MessageFactory {
    public static Message buildMessage(MessageType messageType) {
        Message result = null;

        switch (messageType) {
            case REGISTER_PHONE:
                result = buildRegisterMessage();
        }

        return result;
    }

    private static Message buildRegisterMessage() {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_PHONE)
                                                                      .build();

        //TODO Implements real userId
        Register register = new Register(42);

        return new Message.MessageBuilder().addMessageMeta(messageMeta)
                                           .addRegister(register)
                                           .build();
    }

}
