package com.bench.tlacouque.benchdbfloworm;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by user on 19/11/2015.
 */
public class DbFlowTestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
