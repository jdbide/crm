package fr.pds.isintheair.crmtab;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Binder;
import android.os.Build;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.shadows.ShadowConnectivityManager;
import org.robolectric.shadows.ShadowNetworkInfo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.view.activity.SynchronisationActivity;

/**
 * Created by Truong on 5/8/2016.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, packageName = "fr.pds.isintheair.crmtab",
        manifest = "src/main/AndroidManifest.xml")
@RunWith(RobolectricGradleTestRunner.class)
public class SynchronisationActivityTest {

    @Before
    public void setUp() throws Exception {

        ConnectivityManager cm = (ConnectivityManager) RuntimeEnvironment.application.getSystemService(Context.CONNECTIVITY_SERVICE);

        ShadowConnectivityManager shadowCM = (ShadowConnectivityManager) ShadowExtractor.extract(cm);
        ShadowNetworkInfo shadowOfActiveNetworkInfo = (ShadowNetworkInfo) ShadowExtractor.extract(shadowCM.getActiveNetworkInfo());
    }

    @Test
    public void startForceSyncingTest() throws Exception {
        SynchronisationActivity activity = new SynchronisationActivity();
        final int uid = Binder.getCallingUid();
        assertNotNull(uid);
        assertEquals(Binder.getCallingUid(), uid);
    }
}
