package fr.pds.isintheair.phonintheair;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", sdk = 21, packageName = "fr.pds.isintheair")
public class CallServiceTest {
    @Test
    public void testWebSocketOpenedAfterServiceLaunched() {
    }
}
