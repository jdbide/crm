package fr.pds.isintheair.phonintheair;

/* @RunWith(PowerMockRunner.class)
@PrepareForTest({CallController.class, SharedPreferencesHelper.class})
public class CallWebSocketHandlerTest {
   @Test
    public void testRegisterCalledOnOpen() {
        PowerMockito.mockStatic(CallController.class);
        PowerMockito.mockStatic(SharedPreferencesHelper.class);

        PowerMockito.when(SharedPreferencesHelper.readInteger(anyString(), anyInt())).thenReturn(42);

        CallWebSocketHandler callWebSocketHandler = new CallWebSocketHandler();
        callWebSocketHandler.onOpen();

        verifyStatic();
        CallController.sendRegisterMessage(anyInt());
    }
} */