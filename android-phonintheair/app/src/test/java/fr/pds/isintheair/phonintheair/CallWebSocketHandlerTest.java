package fr.pds.isintheair.phonintheair;

import org.junit.Test;
import org.mockito.Mockito;

import fr.pds.isintheair.phonintheair.model.entity.Message;
import fr.pds.isintheair.phonintheair.model.entity.MessageMeta;
import fr.pds.isintheair.phonintheair.model.entity.Register;
import fr.pds.isintheair.phonintheair.model.enumeration.MessageType;
import fr.pds.isintheair.phonintheair.model.websocket.WebSocketConnectionHandlerSingleton;

public class CallWebSocketHandlerTest {
    @Test
    public void testRegisterCalledOnOpen() {
        WebSocketConnectionHandlerSingleton webSocketConnectionHandlerSingleton = Mockito.spy(WebSocketConnectionHandlerSingleton.getInstance());

        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_PHONE).build();
        Register    register    = new Register(42);
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageMeta).addRegister(register).build();

        WebSocketConnectionHandlerSingleton.getInstance().connect();
        Mockito.verify(webSocketConnectionHandlerSingleton, Mockito.times(1)).sendMessage(message);
    }
}
