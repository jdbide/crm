package fr.pds.isintheair.phonintheair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class WebSocketTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testConnected() {
        /* WebSocketConnectionHandlerSingleton.getInstance().connect();
        Assert.assertTrue(WebSocketConnectionHandlerSingleton.getInstance().isConnected); */
    }

    @Test
    public void testServiceConnectsWebSocket() {
    }
}
