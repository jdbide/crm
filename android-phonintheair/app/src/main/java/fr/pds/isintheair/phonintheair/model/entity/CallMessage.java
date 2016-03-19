package fr.pds.isintheair.phonintheair.model.entity;

/******************************************
 * Created by        :                    *
 * Creation date     : 03/19/16            *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class CallMessage extends Message {
    private Call     call;
    private Register register;

    private CallMessage(Builder messageBuilder) {
        this.call = messageBuilder.call;
        this.register = messageBuilder.register;
    }

    public Call getCall() {
        return call;
    }

    public Register getRegister() {
        return register;
    }

    public static class Builder extends Message.Builder<Builder> {
        public Call     call;
        public Register register;

        @Override
        protected Builder getThis() {
            return this;
        }

        public Builder addCall(Call call) {
            this.call = call;
            return this;
        }

        public Builder addRegister(Register register) {
            this.register = register;
            return this;
        }

        public CallMessage build() {
            return new CallMessage(this);
        }
    }
}
