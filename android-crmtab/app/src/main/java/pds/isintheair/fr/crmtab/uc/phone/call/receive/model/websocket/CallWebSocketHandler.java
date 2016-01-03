package pds.isintheair.fr.crmtab.uc.phone.call.receive.model.websocket;

import de.tavendo.autobahn.WebSocketConnectionHandler;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.controller.MessageController;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.entity.Message;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.entity.MessageFactory;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.util.JSONHelper;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.util.enumeration.MessageType;

public class CallWebSocketHandler extends WebSocketConnectionHandler {
    @Override
    public void onOpen() {
        WebSocketConnectionHandlerSingleton.getInstance()
                                           .sendMessage(JSONHelper.serialize(MessageFactory.buildMessage(
                                                   MessageType.REGISTER_TABLET,
                                                   null), Message.class));
    }

    @Override
    public void onClose(int code, String reason) {
        //TODO Handle close reasons
    }

    @Override
    public void onTextMessage(String payload) {
        if (!payload.isEmpty())
            MessageController.handleMessage((Message) JSONHelper.deserialize(payload,
                                                                             Message.class));
    }
}

