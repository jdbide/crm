package fr.pds.isintheair.entity;

import fr.pds.isintheair.enumeration.MessageType;

public class MessageMeta {
    private Integer code;
    private String message;
    private MessageType messageType;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
