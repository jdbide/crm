package fr.pds.isintheair.crmtab.controller.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import fr.pds.isintheair.crmtab.model.websocket.WebSocketConnectionHandlerSingleton;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 03/19/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class CalendarService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return new CallBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        WebSocketConnectionHandlerSingleton.getInstance().connectToCalendar();

        return START_STICKY;
    }

    public class CallBinder extends Binder {
        CalendarService getService() {
            return CalendarService.this;
        }
    }
}
