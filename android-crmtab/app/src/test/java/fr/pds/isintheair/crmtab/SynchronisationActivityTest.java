package fr.pds.isintheair.crmtab;

import android.os.Binder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.view.activity.SynchronisationActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Truong on 5/8/2016.
 */
public class SynchronisationActivityTest {

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void startForceSyncingTest() throws Exception {
        SynchronisationActivity activity = new SynchronisationActivity();
        final int uid = Binder.getCallingUid();
        Mockito.doNothing().when(activity).createDemoAccount();
        assertNotNull(uid);
        assertEquals(Binder.getCallingUid(), uid);
    }
}
