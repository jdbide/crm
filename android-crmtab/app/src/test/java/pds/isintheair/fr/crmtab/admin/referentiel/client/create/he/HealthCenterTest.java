package pds.isintheair.fr.crmtab.admin.referentiel.client.create.he;

import android.os.Build;

import com.raizlabs.android.dbflow.config.FlowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.Field;

import fr.pds.isintheair.crmtab.BuildConfig;
import fr.pds.isintheair.crmtab.client.create.he.entities.HealthCenter;
import fr.pds.isintheair.crmtab.client.create.he.entities.Holding;
import fr.pds.isintheair.crmtab.client.create.he.entities.PurchasingCentral;

import static org.junit.Assert.assertEquals;

/**
 * Created by tlacouque on 19/01/2016.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, packageName = "pds.isintheair.fr.crmtab")
@RunWith(RobolectricGradleTestRunner.class)
public class HealthCenterTest {
    
    HealthCenter healthCenter = new HealthCenter();
    Holding holdingAliance;
    Holding holdingAucune;
    PurchasingCentral purchasingCentralAucune;
    PurchasingCentral purchasingCentralCAHPP;
    
    @Before
    public void setUp() throws Exception {
        initDB();
    }

    @Test
    public void testGetPurchasingCentral() throws Exception {
        healthCenter.setPurchasingCentralId(purchasingCentralCAHPP.getId());
        assertEquals(purchasingCentralCAHPP.getId(), healthCenter.getPurchasingCentral().getId());
    }

    @Test
    public void testGetHolding() throws Exception {
        healthCenter.setHoldingId(holdingAliance.getId());
        assertEquals(holdingAliance.getId(), healthCenter.getHolding().getId());
    }




    @After
    public void tearDown() throws Exception {
        cleanDb();
        Field field = FlowManager.class.getDeclaredField("mDatabaseHolder");
        field.setAccessible(true);
        field.set(null, null);
    }
    
    public void initDB() {
        holdingAucune = new Holding();
        holdingAucune.setId(0);
        holdingAucune.setName("Aucune");
        holdingAucune.save();

        holdingAliance = new Holding();
        holdingAliance.setId(1);
        holdingAliance.setName("Aliance HealthCare");
        holdingAliance.save();


        purchasingCentralAucune = new PurchasingCentral();
        purchasingCentralAucune.setId(0);
        purchasingCentralAucune.setName("Aucune");
        purchasingCentralAucune.save();

        purchasingCentralCAHPP = new PurchasingCentral();
        purchasingCentralCAHPP.setId(1);
        purchasingCentralCAHPP.setName("CAHPP");
        purchasingCentralCAHPP.save();
    }
    
    public void cleanDb() {
        holdingAliance.delete();
        holdingAucune.delete();
        purchasingCentralAucune.delete();
        purchasingCentralCAHPP.delete();
    }
    
    
}
