package pds.isintheair.fr.crmtab.admin.referentiel.client.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.widget.RecyclerView;

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
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.client.activity.CRUDCustomerActivity;
import fr.pds.isintheair.crmtab.client.create.he.entities.HealthCenter;
import fr.pds.isintheair.crmtab.client.create.indep.entities.Independant;
import fr.pds.isintheair.crmtab.client.fragment.ListCustomerFragment;

import static org.junit.Assert.assertEquals;

/**
 * Created by tlacouque on 20/01/2016.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, packageName = "pds.isintheair.fr.crmtab")
@RunWith(RobolectricGradleTestRunner.class)
public class ListCustomerFragmentTest {
    ListCustomerFragment listCustomerFragment;
    CRUDCustomerActivity activity;
    HealthCenter healthCenter;
    Independant independant;

    ConnectivityManager cm;
    ShadowConnectivityManager shadowCM;
    ShadowNetworkInfo shadowOfActiveNetworkInfo;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(CRUDCustomerActivity.class);
        listCustomerFragment = (ListCustomerFragment)
                activity.getFragmentManager().findFragmentById(R.id.create_customer_fragment_container);
        initDb();

        cm = (ConnectivityManager)
                activity.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        shadowCM = (ShadowConnectivityManager) ShadowExtractor.extract(cm);
        // shadowCM = Shadows.shadowOf(cm);
        shadowOfActiveNetworkInfo = (ShadowNetworkInfo) ShadowExtractor.extract(shadowCM.getActiveNetworkInfo());


    }


    @Test
    public void testInitCustomerWithoutConnexionAndInitAdapter() throws Exception {
        NetworkInfo networkInfo =
                ShadowNetworkInfo.newInstance
                        (NetworkInfo.DetailedState.DISCONNECTED, ConnectivityManager.TYPE_WIFI, 0, false, false);
        shadowCM.setActiveNetworkInfo(networkInfo);
        listCustomerFragment.initCustomers();
        RecyclerView recyclerView =
                (RecyclerView) activity.findViewById(R.id.list_customer_recycler_view);
        assertEquals(2,recyclerView.getAdapter().getItemCount());
    }

    @After
    public void tearDown() throws Exception {
        cleanDb();
        Field field = FlowManager.class.getDeclaredField("mDatabaseHolder");
        field.setAccessible(true);
        field.set(null, null);
    }

    public void initDb() {
        healthCenter = new HealthCenter();
        healthCenter.setSiretNumber(0);
        healthCenter.setName("testHc");
        healthCenter.save();

        independant = new Independant();
        independant.setSiretNumber(0);
        independant.setName("testIndep");
        independant.save();
    }

    public void cleanDb() {
        healthCenter.delete();
        independant.delete();
    }

}
