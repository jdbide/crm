package com.bench.tlacouque.benchmapforge;

import android.app.Application;

import org.mapsforge.map.android.graphics.AndroidGraphicFactory;

/**
 * Created by user on 25/11/2015.
 */
public class BenchMapForgeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidGraphicFactory.createInstance(this);
    }
}
