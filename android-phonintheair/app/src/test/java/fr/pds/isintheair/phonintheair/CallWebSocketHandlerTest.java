package fr.pds.isintheair.phonintheair;

import org.junit.Test;
import org.mockito.Mockito;

import fr.pds.isintheair.phonintheair.entity.Message;
import fr.pds.isintheair.phonintheair.entity.MessageMeta;
import fr.pds.isintheair.phonintheair.entity.Register;
import fr.pds.isintheair.phonintheair.enumeration.MessageType;
import fr.pds.isintheair.phonintheair.websocket.CallWebSocketHandler;
import fr.pds.isintheair.phonintheair.websocket.WebSocketConnectionHandlerSingleton;

public class CallWebSocketHandlerTest {
    @Test
    public void testIsConnected() throws InterruptedException {
        /* WebSocketConnectionHandlerSingleton.getInstance().connect();
        assertTrue(WebSocketConnectionHandlerSingleton.getInstance().isConnected); */
    }

    @Test
    public void testRegisterCalledOnOpen() {
        WebSocketConnectionHandlerSingleton webSocketConnectionHandlerSingleton = Mockito.spy(WebSocketConnectionHandlerSingleton.getInstance());
        CallWebSocketHandler                callWebSocketHandler                = new CallWebSocketHandler();

        callWebSocketHandler.onOpen();

        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_PHONE)
                                                                      .build();

        Register register = new Register(42);

        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addRegister(
                register).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
        Mockito.verify(webSocketConnectionHandlerSingleton, Mockito.times(1)).sendMessage(message);
    }
}
