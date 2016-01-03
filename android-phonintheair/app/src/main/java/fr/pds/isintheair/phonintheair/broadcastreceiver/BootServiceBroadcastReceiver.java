package fr.pds.isintheair.phonintheair.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.pds.isintheair.phonintheair.service.CallService;

public class BootServiceBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            final Intent callServiceIntent = new Intent(context, CallService.class);

            context.startService(callServiceIntent);
        }
    }
}
