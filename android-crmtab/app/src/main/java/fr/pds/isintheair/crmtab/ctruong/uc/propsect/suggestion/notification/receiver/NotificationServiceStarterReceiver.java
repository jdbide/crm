package fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.notification.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Truong on 1/24/2016.
 */
public class NotificationServiceStarterReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationEventReceiver.setUpAlarm(context);
    }
}
