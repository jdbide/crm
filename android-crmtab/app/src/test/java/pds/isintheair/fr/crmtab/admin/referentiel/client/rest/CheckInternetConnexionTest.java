package pds.isintheair.fr.crmtab.admin.referentiel.client.rest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import com.raizlabs.android.dbflow.config.FlowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.shadows.ShadowConnectivityManager;
import org.robolectric.shadows.ShadowNetworkInfo;

import java.lang.reflect.Field;

import fr.pds.isintheair.crmtab.BuildConfig;
import fr.pds.isintheair.crmtab.client.activity.CRUDCustomerActivity;
import fr.pds.isintheair.crmtab.client.rest.CheckInternetConnexion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by tlacouque on 19/01/2016.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, packageName = "pds.isintheair.fr.crmtab")
@RunWith(RobolectricGradleTestRunner.class)
public class CheckInternetConnexionTest {
    CRUDCustomerActivity activity;
    ConnectivityManager cm;
    ShadowConnectivityManager shadowCM;
    ShadowNetworkInfo shadowOfActiveNetworkInfo;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(CRUDCustomerActivity.class);
        // cm = (ConnectivityManager) RuntimeEnvironment.application.getSystemService(Context.CONNECTIVITY_SERVICE);
        cm = (ConnectivityManager)
                activity.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        shadowCM = (ShadowConnectivityManager) ShadowExtractor.extract(cm);
        // shadowCM = Shadows.shadowOf(cm);
        shadowOfActiveNetworkInfo = (ShadowNetworkInfo) ShadowExtractor.extract(shadowCM.getActiveNetworkInfo());
    }

    @Test
    public void testNoInternetConnexionAvailable() throws Exception {

        NetworkInfo networkInfo =
                ShadowNetworkInfo.newInstance
                        (NetworkInfo.DetailedState.DISCONNECTED, ConnectivityManager.TYPE_WIFI, 0, false, false);

       shadowCM.setActiveNetworkInfo(networkInfo);



        assertFalse(CheckInternetConnexion.isNetworkAvailable(activity));
    }

    @Test
    public void testInternetConnexionAvailable() throws Exception {

        NetworkInfo networkInfo =
                ShadowNetworkInfo.newInstance
                        (NetworkInfo.DetailedState.CONNECTED, ConnectivityManager.TYPE_WIFI, 0, true, true);

        shadowCM.setActiveNetworkInfo(networkInfo);

        assertTrue(CheckInternetConnexion.isNetworkAvailable(activity));
    }


    @After
    public void tearDown() throws Exception {
        Field field = FlowManager.class.getDeclaredField("mDatabaseHolder");
        field.setAccessible(true);
        field.set(null, null);
    }


}
