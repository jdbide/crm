package fr.pds.isintheair.phonintheair.model.websocket;

import android.util.Log;

import de.tavendo.autobahn.WebSocketConnectionHandler;
import fr.pds.isintheair.phonintheair.controller.CalendarController;
import fr.pds.isintheair.phonintheair.helper.JSONHelper;
import fr.pds.isintheair.phonintheair.model.entity.CalendarMessage;

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

        CalendarController.sendRegisterMessage();
    }

    @Override
    public void onClose(int code, String reason) {
        Log.d(TAG, "Calendar session closed with code : " + code + " with reason : " + reason);
        WebSocketConnectionHandlerSingleton.getInstance().connectToCalendar();
    }

    @Override
    public void onTextMessage(String payload) {
        Log.d(TAG, "Calendar message received : " + payload);

        if (!payload.isEmpty())
            CalendarController.handleMessage((CalendarMessage) JSONHelper.deserialize(payload, CalendarMessage.class));
    }
}
