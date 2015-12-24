package fr.pds.isintheair.phonintheair.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import fr.pds.isintheair.phonintheair.websocket.WebSocketConnectionHandlerSingleton;

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

        WebSocketConnectionHandlerSingleton.getInstance().connect();

        return START_STICKY;
    }

    public class CallBinder extends Binder {
        CallService getService() {
            return CallService.this;
        }
    }
}
