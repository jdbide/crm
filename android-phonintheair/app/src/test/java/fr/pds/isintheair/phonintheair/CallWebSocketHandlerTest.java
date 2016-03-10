package fr.pds.isintheair.phonintheair;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import fr.pds.isintheair.phonintheair.controller.MessageController;
import fr.pds.isintheair.phonintheair.helper.JSONHelper;
import fr.pds.isintheair.phonintheair.helper.SharedPreferencesHelper;
import fr.pds.isintheair.phonintheair.model.entity.Call;
import fr.pds.isintheair.phonintheair.model.entity.Message;
import fr.pds.isintheair.phonintheair.model.entity.MessageMeta;
import fr.pds.isintheair.phonintheair.model.enumeration.MessageType;
import fr.pds.isintheair.phonintheair.model.websocket.CallWebSocketHandler;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MessageController.class, SharedPreferencesHelper.class})
public class CallWebSocketHandlerTest {
    @Test
    public void testRegisterCalledOnOpen() {
        PowerMockito.mockStatic(MessageController.class);
        PowerMockito.mockStatic(SharedPreferencesHelper.class);

        PowerMockito.when(SharedPreferencesHelper.readInteger(anyString(), anyInt())).thenReturn(42);

        CallWebSocketHandler callWebSocketHandler = new CallWebSocketHandler();
        callWebSocketHandler.onOpen();

        verifyStatic();
        MessageController.sendRegisterMessage(anyInt());
    }

    @Test
    public void testMessageIsHandledOnReceive() {
        PowerMockito.mockStatic(MessageController.class);

        CallWebSocketHandler callWebSocketHandler = new CallWebSocketHandler();
        MessageMeta          messageMeta          = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_PHONE).build();
        Call                 call                 = new Call("0610772364");
        Message              message              = new Message.MessageBuilder().addMessageMeta(messageMeta).addCall(call).build();

        callWebSocketHandler.onTextMessage(JSONHelper.serialize(message, Message.class));

        verifyStatic();
        MessageController.handleMessage(any(Message.class));
    }

}
