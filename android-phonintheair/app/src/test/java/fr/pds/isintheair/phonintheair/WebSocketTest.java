package fr.pds.isintheair.phonintheair;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", sdk = 21, packageName = "fr.pds.isintheair")
public class WebSocketTest {
    @Test
    public void testConnected() {
        /* WebSocketConnectionHandlerSingleton.getInstance().connect();
        Assert.assertTrue(WebSocketConnectionHandlerSingleton.getInstance().isConnected); */
    }

    @Test
    public void testServiceConnectsWebSocket() {
    }
}
