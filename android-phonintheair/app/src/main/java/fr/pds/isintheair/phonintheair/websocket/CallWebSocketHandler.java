package fr.pds.isintheair.phonintheair.websocket;

import android.util.Log;

import de.tavendo.autobahn.WebSocketHandler;
import fr.pds.isintheair.phonintheair.controller.MessageController;
import fr.pds.isintheair.phonintheair.entity.Message;
import fr.pds.isintheair.phonintheair.util.JSONHelper;

public class CallWebSocketHandler extends WebSocketHandler {
    private String TAG = getClass().getSimpleName();

    @Override
    public void onOpen() {
        Log.d(TAG, "Connection opened");

        WebSocketConnectionHandlerSingleton connectionHandlerSingleton = WebSocketConnectionHandlerSingleton
                .getInstance();

        connectionHandlerSingleton.isConnected = true;
        MessageController.sendRegisterMessage();
    }

    @Override
    public void onClose(int code, String reason) {
        Log.d(TAG, "Connection closed");

        WebSocketConnectionHandlerSingleton.getInstance().isConnected = false;
    }

    @Override
    public void onTextMessage(String payload) {
        Log.d(TAG, "Received message : " + payload);

        if (!payload.isEmpty())
            MessageController.handleMessage((Message) JSONHelper.deserialize(payload,
                                                                             Message.class));
    }
}

