package fr.pds.isintheair.phonintheair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pds.isintheair.phonintheair.websocket.WebSocketConnectionHandlerSingleton;


public class WebSocketTest {
    @Before
    public void setUp() throws Exception {
        WebSocketConnectionHandlerSingleton.getInstance().connect();
    }

    @Test
    public void testConnected() {
        Assert.assertTrue(WebSocketConnectionHandlerSingleton.getInstance().isConnected);
    }

    @Test
    public void testServiceConnectsWebSocket() {
    }
}
