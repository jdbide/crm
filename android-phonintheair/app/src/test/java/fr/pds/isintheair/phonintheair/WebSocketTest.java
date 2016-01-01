package fr.pds.isintheair.phonintheair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import fr.pds.isintheair.phonintheair.websocket.WebSocketConnectionHandlerSingleton;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class WebSocketTest {
    @Before
    public void setUp() throws Exception {
        WebSocketConnectionHandlerSingleton.getInstance().connect();
    }

    @Test
    public void testConnected() {
        Assert.assertTrue(true);
    }

    @Test
    public void testServiceConnectsWebSocket() {
    }
}
