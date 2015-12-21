package pds.isintheair.fr.crm_tab.registercall;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Created by j-d on 21/12/2015.
 */
    public class MainThreadBus extends Bus {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override public void post(final Object event) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.post(event);
            } else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        MainThreadBus.super.post(event);
                    }
                });
            }
        }
    }
