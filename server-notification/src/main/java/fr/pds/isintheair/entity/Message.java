package fr.pds.isintheair.entity;

public class Message {
    Call call;
    private MessageMeta messageMeta;
    private Register register;

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
}
