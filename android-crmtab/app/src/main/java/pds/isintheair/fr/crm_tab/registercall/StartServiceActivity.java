package pds.isintheair.fr.crm_tab.registercall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by j-d on 21/12/2015.
 */
public class StartServiceActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Intent intent = new Intent(this, ListennerCallEndedSEvent.class);
        startService(intent);
        Intent intent1 = new Intent(this, StartCallRegisterActivity.class);
        startActivity(intent1);
        finish();
    }
}
