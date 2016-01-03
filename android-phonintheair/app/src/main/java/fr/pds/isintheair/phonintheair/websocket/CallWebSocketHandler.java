package fr.pds.isintheair.phonintheair.websocket;

import de.tavendo.autobahn.WebSocketConnectionHandler;
import fr.pds.isintheair.phonintheair.controller.MessageController;
import fr.pds.isintheair.phonintheair.entity.Message;
import fr.pds.isintheair.phonintheair.util.JSONHelper;

public class CallWebSocketHandler extends WebSocketConnectionHandler {
    private String TAG = getClass().getSimpleName();

    @Override
    public void onOpen() {
        WebSocketConnectionHandlerSingleton.getInstance().isConnected = true;
        MessageController.sendRegisterMessage();
    }

    @Override
    public void onClose(int code, String reason) {
        WebSocketConnectionHandlerSingleton.getInstance().isConnected = false;
    }

    @Override
    public void onTextMessage(String payload) {
        if (!payload.isEmpty() && !payload.equals("Bonjour"))
            MessageController.handleMessage((Message) JSONHelper.deserialize(payload,
                                                                             Message.class));
    }
}

