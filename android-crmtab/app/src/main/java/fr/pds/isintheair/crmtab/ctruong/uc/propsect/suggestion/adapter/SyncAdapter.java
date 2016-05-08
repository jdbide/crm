package fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.adapter;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

/**
 * Created by Truong on 4/24/2016.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private static final String TAG = "SyncAdapter";
    // Global variables
    // Define a variable to contain a content resolver instance
    public static final String SYNC_FINISHED = "SyncFinished";
    public static final String SYNC_STARTED = "SyncStarted";
    final ContentResolver mContentResolver;
    Context context;


    /**
     * Set up a Sync Adapter
     *
     * @param context
     * @param autoInitialize
     */
    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        this.context = context;
        mContentResolver = context.getContentResolver();
        Log.i("SyncAdapter", "SyncAdapter");
    }

    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     *
     * @param context
     * @param autoInitialize
     * @param allowParallelSyncs
     */
    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);

        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SYNC_STARTED);
                context.sendBroadcast(i);
                Log.i("SyncAdapter", "onPerformSync");
                i = new Intent(SYNC_FINISHED);
                context.sendBroadcast(i);
            }
        }, 5000);

    }
}
