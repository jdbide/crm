package fr.pds.isintheair.phonintheair;

import fr.pds.isintheair.phonintheair.websocket.WebSocketConnectionHandlerSingleton;

public class WebSocketTest extends ApplicationTest {
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        WebSocketConnectionHandlerSingleton.getInstance().connect();
    }

    public void testConnected() {
        assertTrue(WebSocketConnectionHandlerSingleton.getInstance().isConnected);
    }
}
