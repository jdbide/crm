package fr.pds.isintheair.phonintheair;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import fr.pds.isintheair.phonintheair.controller.service.CallService;

public class PhonintheairApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        final Intent intent = new Intent(this, CallService.class);

        startService(intent);
    }
}
