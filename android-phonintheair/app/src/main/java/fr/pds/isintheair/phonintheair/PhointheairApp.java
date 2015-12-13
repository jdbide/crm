package fr.pds.isintheair.phonintheair;

import android.app.Application;
import android.content.Context;

public class PhointheairApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }
}
