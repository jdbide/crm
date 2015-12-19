package pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.websocket;

import de.tavendo.autobahn.WebSocketConnection;

public class WebSocketConnectionHandlerSingleton {
    private static WebSocketConnectionHandlerSingleton INSTANCE            = null;
    private        WebSocketConnection                 webSocketConnection = null;

    private WebSocketConnectionHandlerSingleton() {
    }

    public static synchronized WebSocketConnectionHandlerSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WebSocketConnectionHandlerSingleton();
        }

        return INSTANCE;
    }

    public void sendMessage(String message) {
        webSocketConnection.sendTextMessage(message);
    }

    public void setWebSocketConnection(WebSocketConnection webSocketConnection) {
        this.webSocketConnection = webSocketConnection;
    }
}
