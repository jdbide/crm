package pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.entity;

import java.util.HashMap;

import pds.isintheair.fr.crm_tab.uc.phone.call.receive.util.Constant;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.util.enumeration.MessageType;

public class MessageFactory {
    public static Message buildMessage(MessageType messageType, HashMap<String, String> messageInformations) {
        Message result = null;

        switch (messageType) {
            case REGISTER_PHONE:
            case REGISTER_TABLET:
                result = buildRegisterMessage(messageType);
                break;
            case CALL:
                result = buildCallMessage(messageType, messageInformations);
                break;
            case CALL_END:
                result = buildCallEndMessage(messageType);
                break;
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


    private static Message buildCallMessage(MessageType messageType, HashMap<String, String> messageInformations) {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(messageType)
                                                                      .build();

        Call call = new Call(messageInformations.get(Constant.MESSAGE_INFORMATIONS_PHONE_NUMBER));

        return new Message.MessageBuilder().addMessageMeta(messageMeta)
                                           .addCall(call)
                                           .build();
    }


    private static Message buildCallEndMessage(MessageType messageType) {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(messageType)
                                                                      .build();

        return new Message.MessageBuilder().addMessageMeta(messageMeta)
                                           .build();
    }
}
