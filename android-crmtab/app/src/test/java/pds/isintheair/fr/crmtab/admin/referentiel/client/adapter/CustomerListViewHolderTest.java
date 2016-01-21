package pds.isintheair.fr.crmtab.admin.referentiel.client.adapter;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

import pds.isintheair.fr.crmtab.BuildConfig;
import pds.isintheair.fr.crmtab.R;
import pds.isintheair.fr.crmtab.admin.referentiel.client.activity.CRUDCustomerActivity;
import pds.isintheair.fr.crmtab.admin.referentiel.client.create.he.entities.HealthCenter;
import pds.isintheair.fr.crmtab.admin.referentiel.client.create.he.fragment.CreateHCFragment;
import pds.isintheair.fr.crmtab.admin.referentiel.client.create.indep.entities.Independant;
import pds.isintheair.fr.crmtab.admin.referentiel.client.display.hc.fragment.DetailHCFragment;
import pds.isintheair.fr.crmtab.admin.referentiel.client.display.indep.fragment.DetailIndepFragment;
import pds.isintheair.fr.crmtab.admin.referentiel.client.fragment.ListCustomerFragment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by tlacouque on 20/01/2016.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, packageName = "pds.isintheair.fr.crmtab")
@RunWith(RobolectricGradleTestRunner.class)
public class CustomerListViewHolderTest {
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
        NetworkInfo networkInfo =
                ShadowNetworkInfo.newInstance
                        (NetworkInfo.DetailedState.DISCONNECTED, ConnectivityManager.TYPE_WIFI, 0, false, false);
        shadowCM.setActiveNetworkInfo(networkInfo);
        listCustomerFragment.initCustomers();
    }

    @Test
    public void testClickOnHealthCenter() throws Exception {
         final RecyclerView recyclerView =
                (RecyclerView) activity.findViewById(R.id.list_customer_recycler_view);
      /** int i = recyclerView.getAdapter().getItemCount();

        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) recyclerView.getLayoutParams();
        int j = lp.getViewAdapterPosition();


       CustomerListViewHolder viewHolder =
               (CustomerListViewHolder) recyclerView.findViewHolderForLayoutPosition(0);
        viewHolder.onClick(viewHolder.itemView);
       // Thread.sleep(5000);
        //recyclerView.findViewHolderForPosition(0).itemView.performClick();
//        recyclerView.getChildAt(0).performClick();


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.getChildAt(0).performClick();
                recyclerView.scrollToPosition(0);
                Fragment detailHCFragment =
                        activity.getFragmentManager().findFragmentById(R.id.create_customer_fragment_container);
                boolean bool = false;
                if (detailHCFragment instanceof DetailHCFragment) {
                    bool = true;
                }
                assertFalse(bool);
            }
        }, 500);


       */


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
