package pds.isintheair.fr.crm_tab.uc.phone.call.receive.controller.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.websocket.CallWebSocketHandler;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.websocket.WebSocketConnectionHandlerSingleton;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.util.Constant;


public class CallService extends Service {
    private String TAG = getClass().getName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new CallBinder();
    }

    @Override
    public void onCreate() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service started");

        WebSocketConnection webSocketConnection = new WebSocketConnection();
        CallWebSocketHandler callWebSocketHandler = new CallWebSocketHandler();

        WebSocketConnectionHandlerSingleton.getInstance()
                .setWebSocketConnection(webSocketConnection);

        try {
            webSocketConnection.connect(Constant.WS_URL, callWebSocketHandler);
            Log.d(TAG, "Websocket connection is a success");
        } catch (WebSocketException e) {
            Log.d(TAG, "Websocket connection failed : " + e.getMessage());
            //TODO handle exception
        }

        return START_STICKY;
    }

    public class CallBinder extends Binder {
        CallService getService() {
            return CallService.this;
        }
    }
}
