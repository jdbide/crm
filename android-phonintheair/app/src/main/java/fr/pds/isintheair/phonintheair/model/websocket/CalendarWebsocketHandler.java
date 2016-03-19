package fr.pds.isintheair.phonintheair.model.websocket;

import android.util.Log;

import de.tavendo.autobahn.WebSocketConnectionHandler;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 03/19/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class CalendarWebsocketHandler extends WebSocketConnectionHandler {
    private String TAG = getClass().getSimpleName();

    @Override
    public void onOpen() {
        Log.d(TAG, "Calendar session opened");
    }

    @Override
    public void onClose(int code, String reason) {
        Log.d(TAG, "Calendar session closed with code : " + code + " with reason : " + reason);
        WebSocketConnectionHandlerSingleton.getInstance().connectToAgenda();
    }

    @Override
    public void onTextMessage(String payload) {
        Log.d(TAG, "Message received : " + payload);
    }
}
