package fr.pds.isintheair.entity;

public class Message {
    private Call call;
    private MessageMeta messageMeta;
    private Register register;

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

    public static class MessageBuilder {
        private Call call;
        private MessageMeta messageMeta;
        private Register register;

        public MessageBuilder addCall(Call call) {
            this.call = call;
            return this;
        }

        public MessageBuilder addMessageMeta(MessageMeta messageMeta) {
            this.messageMeta = messageMeta;
            return this;
        }

        public Message build() {
            return new Message(this);
        }


    }
}
