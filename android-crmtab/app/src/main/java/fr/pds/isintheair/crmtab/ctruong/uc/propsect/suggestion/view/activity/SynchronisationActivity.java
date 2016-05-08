package fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.view.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.adapter.SyncAdapter;

public class SynchronisationActivity extends Activity implements View.OnClickListener{

    private String TAG = this.getClass().getSimpleName();
    private AccountManager mAccountManager;
    public static final String DEMO_ACCOUNT_NAME = "Demo Account";
    public static final String DEMO_ACCOUNT_PASSWORD = "Demo123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronisation);

        mAccountManager = AccountManager.get(this);

        ((Button) findViewById(R.id.button3)).setOnClickListener(this);
        ((Button) findViewById(R.id.button4)).setOnClickListener(this);

        createDemoAccount();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(syncStaredReceiver, new IntentFilter(SyncAdapter.SYNC_STARTED));
        registerReceiver(syncFinishedReceiver, new IntentFilter(SyncAdapter.SYNC_FINISHED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(syncStaredReceiver);
        unregisterReceiver(syncFinishedReceiver);
    }

    private BroadcastReceiver syncFinishedReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Sync finished!");
            Toast.makeText(getApplicationContext(), "Sync Finished", Toast.LENGTH_SHORT).show();
        }
    };

    private BroadcastReceiver syncStaredReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Sync started!");
            Toast.makeText(getApplicationContext(), "Sync started...", Toast.LENGTH_SHORT).show();
        }
    };

    private void startForceSyncing() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        Account account = new Account(DEMO_ACCOUNT_NAME, getString(R.string.auth_type));
        ContentResolver.requestSync(account, getString(R.string.content_authority), bundle);

        ContentResolver.setIsSyncable(account, getString(R.string.content_authority), 1);
        ContentResolver.setSyncAutomatically(account, getString(R.string.content_authority), true);
    }

    private void scheduleSync() {
        Bundle bundle = new Bundle();
        Account account = new Account(DEMO_ACCOUNT_NAME, getString(R.string.auth_type));
        ContentResolver.requestSync(account, getString(R.string.content_authority), bundle);
        ContentResolver.addPeriodicSync(account, getString(R.string.content_authority), bundle, 15*60);
    }

    void createDemoAccount() {
        Account account = new Account(DEMO_ACCOUNT_NAME, getString(R.string.auth_type));
        boolean accountCreated = mAccountManager.addAccountExplicitly(account, DEMO_ACCOUNT_PASSWORD, null);
        if (accountCreated) {
            showMessage("Account Created");
        }
    }

    private void showMessage(final String msg) {
        if (TextUtils.isEmpty(msg))
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button3) {
            startForceSyncing();
        }
        if (v.getId() == R.id.button4) {
            scheduleSync();
        }
    }
}
