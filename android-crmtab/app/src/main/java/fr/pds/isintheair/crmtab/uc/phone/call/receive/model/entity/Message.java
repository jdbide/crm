package fr.pds.isintheair.crmtab.uc.phone.call.receive.model.entity;

import fr.pds.isintheair.crmtab.uc.phone.call.receive.model.enumeration.DeviceType;

public class Message {
    DeviceType deviceType;
    private Call        call;
    private MessageMeta messageMeta;
    private Register    register;

    private Message(MessageBuilder messageBuilder) {
        this.call = messageBuilder.call;
        this.messageMeta = messageBuilder.messageMeta;
        this.register = messageBuilder.register;
    }

    public MessageMeta getMessageMeta() {
        return messageMeta;
    }

    public void setMessageMeta(MessageMeta messageMeta) {
        this.messageMeta = messageMeta;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public static class MessageBuilder {
        public Call        call;
        public MessageMeta messageMeta;
        public Register    register;

        public MessageBuilder addCall(Call call) {
            this.call = call;
            return this;
        }

        public MessageBuilder addMessageMeta(MessageMeta messageMeta) {
            this.messageMeta = messageMeta;
            return this;
        }

        public MessageBuilder addRegister(Register register) {
            this.register = register;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
