package fr.pds.isintheair.crmtab.common;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.raizlabs.android.dbflow.config.FlowManager;

import fr.pds.isintheair.crmtab.uc.phone.call.receive.controller.service.CallService;

public class CrmTabApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);

        context = getApplicationContext();

        final Intent intent = new Intent(this, CallService.class);

        startService(intent);
    }
}
