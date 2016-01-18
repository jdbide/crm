package pds.isintheair.fr.crmtab.client.create.he.test;

import android.os.Build;
import android.widget.Spinner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import pds.isintheair.fr.crmtab.BuildConfig;
import pds.isintheair.fr.crmtab.R;
import pds.isintheair.fr.crmtab.admin.referentiel.client.activity.CRUDCustomerActivity;
import pds.isintheair.fr.crmtab.admin.referentiel.client.create.he.entities.HealthCenter;
import pds.isintheair.fr.crmtab.admin.referentiel.client.create.he.fragment.CreateHCFragment;
import pds.isintheair.fr.crmtab.admin.referentiel.client.message.MessageRestCustomer;
import pds.isintheair.fr.crmtab.admin.referentiel.client.message.ResponseRestCustomer;
import pds.isintheair.fr.crmtab.admin.referentiel.client.rest.RESTCustomerHandlerSingleton;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by tlacouque on 19/12/2015.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.M)
@RunWith(RobolectricGradleTestRunner.class)
public class CreateHCFragmentTest {
    private CRUDCustomerActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(CRUDCustomerActivity.class);
        activity.runOnUiThread(new Runnable() {
            public void run() {
                activity.onPositiveClick(0);
            }
        });
    }


    @Test
    public void testUISpinerInit() throws Exception {
        Spinner spinner = (Spinner) activity.findViewById(R.id.create_he_fragment_etablishment_type);
        assertEquals(spinner.getAdapter().getCount(), 8);
    }
}








