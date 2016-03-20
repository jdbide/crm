package fr.pds.isintheair.phonintheair;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import fr.pds.isintheair.phonintheair.controller.CallController;
import fr.pds.isintheair.phonintheair.helper.SharedPreferencesHelper;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CallController.class, SharedPreferencesHelper.class})
public class CallWebSocketHandlerTest {
/*    @Test
    public void testRegisterCalledOnOpen() {
        PowerMockito.mockStatic(CallController.class);
        PowerMockito.mockStatic(SharedPreferencesHelper.class);

        PowerMockito.when(SharedPreferencesHelper.readInteger(anyString(), anyInt())).thenReturn(42);

        CallWebSocketHandler callWebSocketHandler = new CallWebSocketHandler();
        callWebSocketHandler.onOpen();

        verifyStatic();
        CallController.sendRegisterMessage(anyInt());
    } */
}