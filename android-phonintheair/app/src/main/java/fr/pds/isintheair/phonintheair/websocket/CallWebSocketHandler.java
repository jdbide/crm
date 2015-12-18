package fr.pds.isintheair.phonintheair.websocket;

import android.util.Log;

import de.tavendo.autobahn.WebSocketHandler;
import fr.pds.isintheair.phonintheair.controller.MessageController;
import fr.pds.isintheair.phonintheair.entity.Message;
import fr.pds.isintheair.phonintheair.entity.MessageFactory;
import fr.pds.isintheair.phonintheair.enumeration.MessageType;
import fr.pds.isintheair.phonintheair.util.JSONHelper;

public class CallWebSocketHandler extends WebSocketHandler {
    @Override
    public void onOpen() {
        Log.d("WS", "Connection opened");

        WebSocketConnectionHandlerSingleton.getInstance()
                                           .sendMessage(JSONHelper.serialize(MessageFactory.buildMessage(MessageType.REGISTER_PHONE), Message.class));
    }

    @Override
    public void onClose(int code, String reason) {
        Log.d("WS", "Connection closed");
    }

    @Override
    public void onTextMessage(String payload) {
        if (!payload.isEmpty())
            MessageController.handleMessage((Message) JSONHelper.deserialize(payload, Message.class));
    }
}

