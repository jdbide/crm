package pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.websocket;

import android.util.Log;

import de.tavendo.autobahn.WebSocketHandler;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.controller.MessageController;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.entity.Message;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.entity.MessageFactory;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.util.JSONHelper;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.util.enumeration.MessageType;

public class CallWebSocketHandler extends WebSocketHandler {
    private String LOG_TAG = getClass().getSimpleName();

    @Override
    public void onOpen() {
        Log.d(LOG_TAG, "Connection opened");

        WebSocketConnectionHandlerSingleton.getInstance()
                .sendMessage(JSONHelper.serialize(MessageFactory.buildMessage(MessageType.REGISTER_TABLET), Message.class));
    }

    @Override
    public void onClose(int code, String reason) {
        Log.d(LOG_TAG, "Connection closed");
    }

    @Override
    public void onTextMessage(String payload) {
        Log.d(LOG_TAG, "Received message : " + payload);

        if (!payload.isEmpty())
            MessageController.handleMessage((Message) JSONHelper.deserialize(payload, Message.class));
    }
}

