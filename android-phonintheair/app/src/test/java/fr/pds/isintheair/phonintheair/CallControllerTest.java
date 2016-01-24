package fr.pds.isintheair.phonintheair;

import org.junit.Test;
import org.mockito.Mockito;

import fr.pds.isintheair.phonintheair.controller.CallController;
import fr.pds.isintheair.phonintheair.model.entity.Call;
import fr.pds.isintheair.phonintheair.model.entity.Message;
import fr.pds.isintheair.phonintheair.model.entity.MessageMeta;
import fr.pds.isintheair.phonintheair.model.enumeration.MessageType;
import fr.pds.isintheair.phonintheair.model.websocket.WebSocketConnectionHandlerSingleton;

/******************************************
 * Created by        :                    *
 * Creation date     : 01/24/16            *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class CallControllerTest {
    @Test
    public void testCallSendsMessage() {
        WebSocketConnectionHandlerSingleton webSocketConnectionHandlerSingleton = Mockito.spy(WebSocketConnectionHandlerSingleton.getInstance());

        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_PHONE).build();
        Call        call        = new Call("0610772364");
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageMeta).addCall(call).build();

        WebSocketConnectionHandlerSingleton.getInstance().connect();
        CallController.call("0610772364");
        Mockito.verify(webSocketConnectionHandlerSingleton, Mockito.times(1)).sendMessage(message);
    }
}
