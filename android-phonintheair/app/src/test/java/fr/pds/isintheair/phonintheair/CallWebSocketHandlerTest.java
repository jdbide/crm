package fr.pds.isintheair.phonintheair;

import org.junit.Test;

public class CallWebSocketHandlerTest {
    @Test
    public void testRegisterCalledOnOpen() {
        /* WebSocketConnectionHandlerSingleton webSocketConnectionHandlerSingleton = Mockito.spy(WebSocketConnectionHandlerSingleton.getInstance());
        SharedPreferences                   sharedPreferences                   = Mockito.mock(SharedPreferences.class);
        WebSocketConnection                 webSocketConnection                 = Mockito.mock(WebSocketConnection.class);

        PhonintheairApp.context = Mockito.mock(Context.class);

        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_PHONE).build();
        Register    register    = new Register(42);
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageMeta).addRegister(register).build();

        doReturn(sharedPreferences).when(PhonintheairApp.context).getSharedPreferences("pref", 0);
        doReturn(42).when(sharedPreferences).getInt(anyString(), anyInt());
        doNothing().when(webSocketConnectionHandlerSingleton).sendMessage((Message) anyObject());

        CallWebSocketHandler callWebSocketHandler = new CallWebSocketHandler();
        callWebSocketHandler.onOpen();

        Mockito.verify(webSocketConnection, Mockito.times(1)).sendTextMessage(anyString()); */
    }
}
