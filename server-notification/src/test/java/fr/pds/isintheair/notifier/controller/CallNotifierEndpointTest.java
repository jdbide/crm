package fr.pds.isintheair.notifier.controller;


import fr.pds.isintheair.notifier.server.CallNotifierEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.logging.Logger;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CallMessageController.class, Logger.class})
public class CallNotifierEndpointTest {
    @Test
    public void testMessageIsHandledWhenReceiving() throws Exception {
        PowerMockito.mockStatic(CallMessageController.class);
        PowerMockito.mockStatic(Logger.class);

        Logger logger = mock(Logger.class);
        doNothing().when(logger).info(anyString());
        PowerMockito.when(Logger.getLogger(anyString())).thenReturn(logger);


        CallNotifierEndpoint callNotifierEndpoint = new CallNotifierEndpoint();
        callNotifierEndpoint.onMessage(anyString(), anyObject());

        PowerMockito.verifyStatic();
        CallMessageController.handleMessage(anyObject(), anyObject());
    }
}
