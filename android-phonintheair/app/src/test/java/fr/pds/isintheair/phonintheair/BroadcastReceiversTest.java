package fr.pds.isintheair.phonintheair;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.BroadcastReceiverData;
import org.robolectric.shadows.ShadowApplication;

import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.DEFAULT, sdk = 21, packageName = "fr.pds.isintheair")
public class BroadcastReceiversTest {
    @Test
    public void testBroadcastReceiversInManifest() {
        ShadowApplication shadowApplication = ShadowApplication.getInstance();
        List<BroadcastReceiverData> receivers = shadowApplication.getAppManifest()
                                                                 .getBroadcastReceivers();

        assertFalse(receivers.isEmpty());
    }
}
