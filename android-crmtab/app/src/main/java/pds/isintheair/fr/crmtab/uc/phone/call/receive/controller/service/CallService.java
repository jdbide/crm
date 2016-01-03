package pds.isintheair.fr.crmtab.uc.phone.call.receive.controller.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.websocket.CallWebSocketHandler;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.websocket.WebSocketConnectionHandlerSingleton;
import pds.isintheair.fr.crmtab.uc.phone.call.receive.util.Constant;

public class CallService extends Service {
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
        WebSocketConnection webSocketConnection = new WebSocketConnection();
        CallWebSocketHandler callWebSocketHandler = new CallWebSocketHandler();

        WebSocketConnectionHandlerSingleton.getInstance()
                                           .setWebSocketConnection(webSocketConnection);

        try {
            webSocketConnection.connect(Constant.WS_URL, callWebSocketHandler);
        }
        catch (WebSocketException e) {
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
