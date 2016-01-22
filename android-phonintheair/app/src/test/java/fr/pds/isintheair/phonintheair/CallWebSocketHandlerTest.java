package fr.pds.isintheair.phonintheair;

import org.junit.Test;
<<<<<<< HEAD
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
=======
>>>>>>> dev

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.DEFAULT, sdk = 21, packageName = "fr.pds.isintheair")
public class CallWebSocketHandlerTest {
    @Test
    public void testIsConnected() throws InterruptedException {
        /* WebSocketConnectionHandlerSingleton.getInstance().connect();
        assertTrue(WebSocketConnectionHandlerSingleton.getInstance().isConnected); */
    }
<<<<<<< HEAD

    @Test
    public void testRegisterCalledOnOpen() {
        /* WebSocketConnectionHandlerSingleton webSocketConnectionHandlerSingleton = Mockito.spy(WebSocketConnectionHandlerSingleton.getInstance());
        CallWebSocketHandler                callWebSocketHandler                = new CallWebSocketHandler();

        callWebSocketHandler.onOpen();

        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_PHONE)
                                                                      .build();

        Register register = new Register(42);

        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addRegister(
                register).build();

        WebSocketConnectionHandlerSingleton.getInstance().connect();
        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
        Mockito.verify(webSocketConnectionHandlerSingleton, Mockito.times(1)).sendMessage(message); */
    }
=======
>>>>>>> dev
}
