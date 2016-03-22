package fr.pds.isintheair.phonintheair;

/* @RunWith(PowerMockRunner.class)
@PrepareForTest({CallMessageController.class, SharedPreferencesHelper.class})
public class CallWebSocketHandlerTest {
   @Test
    public void testRegisterCalledOnOpen() {
        PowerMockito.mockStatic(CallMessageController.class);
        PowerMockito.mockStatic(SharedPreferencesHelper.class);

        PowerMockito.when(SharedPreferencesHelper.readInteger(anyString(), anyInt())).thenReturn(42);

        CallWebSocketHandler callWebSocketHandler = new CallWebSocketHandler();
        callWebSocketHandler.onOpen();

        verifyStatic();
        CallMessageController.sendRegisterMessage(anyInt());
    }
} */