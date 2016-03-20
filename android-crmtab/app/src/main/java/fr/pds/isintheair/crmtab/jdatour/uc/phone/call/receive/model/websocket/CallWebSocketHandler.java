package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.websocket;

import android.util.Log;

import de.tavendo.autobahn.WebSocketConnectionHandler;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.CallController;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.helper.JSONHelper;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.CallMessage;

public class CallWebSocketHandler extends WebSocketConnectionHandler {
    private String TAG = getClass().getSimpleName();

    @Override
    public void onOpen() {
        Log.d(TAG, "Call session opened");

        CallController.sendRegisterMessage();
    }

    @Override
    public void onClose(int code, String reason) {
        Log.d(TAG, "Call session closed with code : " + code + " with reason : " + reason);
        WebSocketConnectionHandlerSingleton.getInstance().connectToCall();
    }

    @Override
    public void onTextMessage(String payload) {
        Log.d(TAG, "Call message received : " + payload);

        if (!payload.isEmpty())
            CallController.handleMessage((CallMessage) JSONHelper.deserialize(payload, CallMessage.class));
    }
}
