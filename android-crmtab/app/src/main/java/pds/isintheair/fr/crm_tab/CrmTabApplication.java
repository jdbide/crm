package pds.isintheair.fr.crm_tab;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by user on 08/12/2015.
 */
public class CrmTabApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
