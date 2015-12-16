package pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.entity;

import pds.isintheair.fr.crm_tab.uc.phone.call.receive.util.enumeration.MessageType;

public class MessageFactory {
    public static Message buildMessage(MessageType messageType) {
        Message result = null;

        switch (messageType) {
            case REGISTER_PHONE:
            case REGISTER_TABLET:
                result = buildRegisterMessage(messageType);
        }

        return result;
    }

    private static Message buildRegisterMessage(MessageType messageType) {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(messageType)
                .build();

        //TODO Implements real userId
        Register register = new Register(42);

        return new Message.MessageBuilder().addMessageMeta(messageMeta)
                .addRegister(register)
                .build();
    }

}
