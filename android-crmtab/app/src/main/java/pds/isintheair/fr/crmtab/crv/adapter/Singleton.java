package pds.isintheair.fr.crmtab.crv.adapter;

import com.squareup.otto.Bus;

/**
 * Created by j-d on 21/12/2015.
 */
public class Singleton {
    private  static Singleton instance = null;


    private  Bus bus;

    private Singleton(){
        bus = new Bus();

    }

    public static Singleton getInstance(){
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }


    public  Bus getBus(){

        if(bus == null)
        {
            bus = new Bus();
        }
        return bus;
    }



}
