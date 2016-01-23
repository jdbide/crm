package fr.pds.isintheair.phonintheair.controller.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import fr.pds.isintheair.phonintheair.controller.MessageController;

/**
 * Broadcast receiver to handle outgoing and incoming calls
 */
public class PhoneCallBroadcastReceiver extends BroadcastReceiver {
    private String TAG = getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_NEW_OUTGOING_CALL.equals(intent.getAction())) {
            Log.d(TAG, "Outgoing call");
        }
        else {
            String state = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);

            Log.d(TAG, "State : " + state);

            if (state != null && state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String phoneNumber = intent.getExtras()
                                           .getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

                Log.d(TAG, "Receiving call from : " + phoneNumber);

                MessageController.sendCallReceivedMessage(phoneNumber);
            }

            else if (state != null && state.equals(TelephonyManager.EXTRA_STATE_IDLE))
                MessageController.sendEndCallMessage();
        }
    }
}