package jdatour.uc.phone.call.receive;

import org.junit.Test;
import org.mockito.Mockito;

import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.Message;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.MessageMeta;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.Register;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.enumeration.MessageType;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.websocket.WebSocketConnectionHandlerSingleton;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/24/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

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