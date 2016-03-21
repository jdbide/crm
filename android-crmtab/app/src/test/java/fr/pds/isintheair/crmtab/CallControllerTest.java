/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/24/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/
/* public class CallControllerTest {
    @Test
    public void testCallSendsMessage() throws Exception {
        /* mockStatic(Log.class);
        mockStatic(NetworkHelper.class);
        mockStatic(WebSocketConnection.class);

        when(Log.d(anyString(), anyString())).thenReturn(0);
        when(NetworkHelper.isNetworkAvailable()).thenReturn(true);

        WebSocketConnection webSocketConnection = mock(WebSocketConnection.class);

        doNothing().when(webSocketConnection).sendTextMessage(anyString());
        whenNew(WebSocketConnection.class).withAnyArguments().thenReturn(webSocketConnection);

        WebSocketConnectionHandlerSingleton webSocketConnectionHandlerSingleton = spy(WebSocketConnectionHandlerSingleton.getInstance());

        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_PHONE).build();
        Call        call        = new Call("0610772364");
        Message     message     = new Message.MessageBuilder().addMessageInfo(messageMeta).addCall(call).build();

        CallController.call("0610772364");
        verify(webSocketConnectionHandlerSingleton, times(1)).sendMessage(message);
} */

