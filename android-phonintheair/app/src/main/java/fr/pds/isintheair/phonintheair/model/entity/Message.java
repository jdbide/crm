package fr.pds.isintheair.phonintheair.model.entity;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/24/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class Message {
    private MessageMeta messageMeta;

    public Message() {
    }

    protected Message(Builder messageBuilder) {
        this.messageMeta = messageBuilder.messageMeta;
    }

    public MessageMeta getMessageMeta() {
        return messageMeta;
    }

    public abstract static class Builder<T extends Builder<T>> {
        public MessageMeta messageMeta;

        protected abstract T getThis();

        public T addMessageMeta(MessageMeta messageMeta) {
            this.messageMeta = messageMeta;
            return getThis();
        }

        public Message build() {
            return new Message(this);
        }
    }
}
