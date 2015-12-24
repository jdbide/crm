package fr.pds.isintheair.phonintheair.websocket;

import android.util.Log;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import fr.pds.isintheair.phonintheair.util.Constant;

public class WebSocketConnectionHandlerSingleton {
    private static WebSocketConnectionHandlerSingleton INSTANCE = null;
    public Boolean isConnected = false;
    private String TAG = getClass().getSimpleName();
    private WebSocketConnection webSocketConnection = null;

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

    public void sendMessage(String message) {
        webSocketConnection.sendTextMessage(message);
    }
}
