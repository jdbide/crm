package fr.pds.isintheair.notifier;

import fr.pds.isintheair.notifier.controller.CalendarMessageController;
import fr.pds.isintheair.notifier.server.CalendarNotifierEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 03/23/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

@RunWith(PowerMockRunner.class)
@PrepareForTest({CalendarMessageController.class})
public class CalendarNotifierEndpointTest {
    @Test
    public void testMessageIsHandledWhenReceiving () throws Exception {
        PowerMockito.mockStatic(CalendarMessageController.class);

        CalendarNotifierEndpoint calendarNotifierEndpoint = new CalendarNotifierEndpoint();
        calendarNotifierEndpoint.onMessage(anyString(), anyObject());

        PowerMockito.verifyStatic();
        CalendarMessageController.handleMessage(anyObject(), anyObject());
    }
}
