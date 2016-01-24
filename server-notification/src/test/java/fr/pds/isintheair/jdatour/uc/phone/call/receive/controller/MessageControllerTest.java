package fr.pds.isintheair.jdatour.uc.phone.call.receive.controller;

import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.Message;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.MessageMeta;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.Register;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.DeviceType;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.MessageType;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.peer.PeerHandlerSingleton;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.websocket.Session;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class MessageControllerTest {
    Session session;

    @Before
    public void setUp() {
        session = Mockito.mock(Session.class);
    }

    @Test
    public void testRegisterPhone() {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_PHONE).build();
        Register register = new Register(42);
        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addRegister(register).build();

        MessageController.handleMessage(message, session);

        assertNotNull(PeerHandlerSingleton.getInstance().findPeerSession(DeviceType.PHONE, 42));
        assertEquals((int) PeerHandlerSingleton.getInstance().findPeerUserId(DeviceType.PHONE, session), 42);
    }
}
