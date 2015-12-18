package badge.system.pds.crmbadge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StatusReceiver extends BroadcastReceiver {
    public StatusReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
// Extract data included in the Intent
        CharSequence intentData = intent.getCharSequenceExtra("status");
        Toast.makeText(context, "Javacodegeeks received the Intent's message: " + intentData, Toast.LENGTH_LONG).show();

    }
}
