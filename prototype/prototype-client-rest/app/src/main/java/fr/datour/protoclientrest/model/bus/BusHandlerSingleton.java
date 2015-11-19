package fr.datour.protoclientrest.model.bus;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import fr.datour.protoclientrest.model.orm.DAL;

public class BusHandlerSingleton {
    private static BusHandlerSingleton mInstance;
    private final  Bus                 bus;

    private BusHandlerSingleton() {
        bus = new Bus(ThreadEnforcer.ANY);

        DAL dal = new DAL();

        bus.register(dal);
    }

    public static synchronized BusHandlerSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new BusHandlerSingleton();
        }

        return mInstance;
    }

    public Bus getBus() {
        return bus;
    }
}