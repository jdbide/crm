package fr.pds.isintheair.crmtab.controller.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;

import fr.pds.isintheair.crmtab.controller.bus.BusHandlerSingleton;
import fr.pds.isintheair.crmtab.controller.service.CallService;
import fr.pds.isintheair.crmtab.controller.service.NotifyPresenceService;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/23/16           *
 * Modified by       : jbide              *
 * Modification date :  29/03/16          *
 ******************************************/

public class BootServiceBrodcastReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            final Intent serviceIntent = new Intent(context, CallService.class);

            context.startService(serviceIntent);
            context.startService(new Intent(context, NotifyPresenceService.class));
        }


        if(intent.getAction().equals(NfcAdapter.ACTION_NDEF_DISCOVERED)){

            System.out.println("Receiver :  ACTION_NDEF_DISCOVERED");
            BusHandlerSingleton.getInstance().getBus().register(this);
            BusHandlerSingleton.getInstance().getBus().post(intent);


        }
        if(intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)){
            System.out.println("Receiver : ACTION_TAG_DISCOVERED ");
        }
    }
}
