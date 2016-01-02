package pds.isintheair.fr.crmtab.uc.phone.call.receive.controller.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import pds.isintheair.fr.crmtab.uc.phone.call.receive.controller.service.CallService;

public class BootServiceBrodcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            final Intent serviceIntent = new Intent(context, CallService.class);

            context.startService(serviceIntent);
        }
    }
}
