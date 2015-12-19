package pds.isintheair.fr.crm_tab;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowManager;

public class CrmTabApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);

        context = getApplicationContext();
    }
}
