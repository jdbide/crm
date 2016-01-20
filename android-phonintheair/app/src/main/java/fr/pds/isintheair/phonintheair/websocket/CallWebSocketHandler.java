package fr.pds.isintheair.phonintheair.websocket;

import android.util.Log;

import de.tavendo.autobahn.WebSocketConnectionHandler;
import fr.pds.isintheair.phonintheair.controller.MessageController;
import fr.pds.isintheair.phonintheair.entity.Message;
import fr.pds.isintheair.phonintheair.util.JSONHelper;

public class CallWebSocketHandler extends WebSocketConnectionHandler {
    private String TAG = getClass().getSimpleName();

    @Override
    public void onOpen() {
        Log.d(TAG, "Session opened");
        WebSocketConnectionHandlerSingleton.getInstance().isConnected = true;
        MessageController.sendRegisterMessage();
    }

    @Override
    public void onClose(int code, String reason) {
        Log.d(TAG, "Session closed with code : " + code + " with reason : " + reason);
        WebSocketConnectionHandlerSingleton.getInstance().isConnected = false;
    }

    @Override
    public void onTextMessage(String payload) {
        Log.d(TAG, "Message received : " + payload);

        //TODO Remove "Bonjour" -> here to test that server responds in dev environnement
        if (!payload.isEmpty() && !payload.equals("Bonjour"))
            MessageController.handleMessage((Message) JSONHelper.deserialize(payload,
                                                                             Message.class));
    }
}

