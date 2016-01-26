package fr.pds.isintheair.jdatour.uc.phone.call.receive.controller;


import fr.pds.isintheair.jdatour.uc.phone.call.receive.server.NotifierEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MessageController.class)
public class NotifierEndpointTest {
    @Test
    public void testMessageIsHandledWhenReceiving() {
        PowerMockito.mockStatic(MessageController.class);

        NotifierEndpoint notifierEndpoint = new NotifierEndpoint();
        notifierEndpoint.onMessage(anyString(), anyObject());

        PowerMockito.verifyStatic();
        MessageController.handleMessage(anyObject(), anyObject());
    }
}
