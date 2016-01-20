package pds.isintheair.fr.crmtab.uc.phone.call.receive.model.websocket;

import android.util.Log;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.entity.Message;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.util.Constant;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.util.JSONHelper;

public class WebSocketConnectionHandlerSingleton {
    private static WebSocketConnectionHandlerSingleton INSTANCE            = null;
    private        String                              TAG                 = getClass().getSimpleName();
    private        WebSocketConnection                 webSocketConnection = null;

    private WebSocketConnectionHandlerSingleton() {
        webSocketConnection = new WebSocketConnection();
    }

    public static synchronized WebSocketConnectionHandlerSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WebSocketConnectionHandlerSingleton();
        }

        return INSTANCE;
    }

    public void connect() {
        CallWebSocketHandler callWebSocketHandler = new CallWebSocketHandler();

        try {
            webSocketConnection.connect(Constant.WS_URL, callWebSocketHandler);
        }
        catch (WebSocketException e) {
            Log.d(TAG, "Websocket connection failed : " + e.getMessage());
            //TODO handle exception
        }
    }

    public void sendMessage(Message message) {
        String serializedMessage = JSONHelper.serialize(message, Message.class);
        Log.d(TAG, "Sending : " + serializedMessage);
        webSocketConnection.sendTextMessage(serializedMessage);
    }
}
