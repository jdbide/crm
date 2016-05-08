package fr.pds.isintheair.crmtab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.adapter.SyncAdapter;

/**
 * Created by Truong on 5/8/2016.
 */
public class SyncAdapterTest {
    Context context;

    @Test
    public void onPerformSyncTest() throws Exception {
        String DEMO_ACCOUNT_NAME = "Demo Account";
        SyncAdapter adapter = new SyncAdapter(context, true);
        Intent intent = new Intent();
        Bundle bundle = intent.getExtras();
        assertNotNull(bundle);
    }
}
