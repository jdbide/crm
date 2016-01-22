package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity;

import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.enumeration.MessageType;

public class MessageMeta {
    private MessageType messageType;
    private Integer statusCode;
    private String errorDescription;

    public MessageMeta(MessageMetaBuilder messageMetaBuilder) {
        this.messageType = messageMetaBuilder.messageType;
        this.statusCode = messageMetaBuilder.statusCode;
        this.errorDescription = messageMetaBuilder.errorDescription;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public static class MessageMetaBuilder {
        private MessageType messageType;
        private Integer statusCode;
        private String errorDescription;

        public MessageMetaBuilder addMessageType(MessageType messageType) {
            this.messageType = messageType;

            return this;
        }

        public MessageMetaBuilder addStatusCode(Integer statusCode) {
            this.statusCode = statusCode;

            return this;
        }

        public MessageMetaBuilder addErrorDescription(String errorDescription) {
            this.errorDescription = errorDescription;

            return this;
        }

        public MessageMeta build() {
            return new MessageMeta(this);
        }
    }
}
