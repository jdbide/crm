package fr.pds.isintheair.endpoint;

import fr.pds.isintheair.PeerHandlerSingleton;
import fr.pds.isintheair.enumeration.PeerType;
import org.junit.Test;
import org.mockito.Mockito;

import javax.websocket.Session;

import static org.junit.Assert.assertNull;

public class NotifierEndpointTest {

    @Test
    public void testOnClose() throws Exception {
        Session phoneSession = Mockito.mock(Session.class);
        PeerHandlerSingleton peerHandlerSingleton = PeerHandlerSingleton.getInstance();

        peerHandlerSingleton.addPeer(PeerType.PHONE, 42, phoneSession);
        new NotifierEndpoint().onClose(phoneSession, null);
        assertNull(peerHandlerSingleton.findPeerSession(PeerType.PHONE, 42));
    }
}