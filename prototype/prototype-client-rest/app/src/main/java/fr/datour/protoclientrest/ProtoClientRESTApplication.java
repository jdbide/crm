package fr.datour.protoclientrest;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

public class ProtoClientRESTApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
