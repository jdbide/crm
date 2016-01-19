package pds.isintheair.fr.crmtab.uc.phone.call.receive.controller.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import pds.isintheair.fr.crmtab.uc.phone.call.receive.model.websocket.WebSocketConnectionHandlerSingleton;

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
        WebSocketConnectionHandlerSingleton.getInstance().connect();

        return START_STICKY;
    }

    public class CallBinder extends Binder {
        CallService getService() {
            return CallService.this;
        }
    }
}
